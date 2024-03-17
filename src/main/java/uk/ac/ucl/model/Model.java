package uk.ac.ucl.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Model {
    private DataFrame dataFrame;

    public Model() {
        this.dataFrame = new DataFrame();
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

    public void loadDataFromCSV(String filePath) {
        DataLoader dataLoader = new DataLoader(filePath);
        this.dataFrame = dataLoader.loadData();
    }

    public Map<String, String> getPatientDetailsFromID(String patientID) {
        Map<String, String> patientDetails = new HashMap<>();

        int rowIndex = dataFrame.getRowIndexFromID(patientID);

        if (rowIndex != -1) {
            for (String columnName : dataFrame.getColumnNames()) {
                String value = getValueWithDefault(columnName, rowIndex);
                patientDetails.put(columnName, value);
            }
        }
        return patientDetails;
    }

    private String getValueWithDefault(String columnName, int row) {
        try {
            String value = dataFrame.getValue(columnName, row);
            if (value != null && !value.isEmpty()) {
                return value;
            }
        } catch (IllegalArgumentException e) {
            // value is set to N/A if column doesn't exist or value is empty
        }
        return "N/A";
    }

    public List<String> searchFor(String keyword, String searchColumn) {
        List<String> searchResults = new ArrayList<>();
        List<String> searchableColumns = switch (searchColumn) {
            case "name" -> Arrays.asList("FIRST", "LAST"); // searches in these columns
            case "id" -> List.of("ID");
            case "ssn" -> List.of("SSN");
            case "drivers" -> List.of("DRIVERS");
            case "race-ethnicity" -> Arrays.asList("RACE", "ETHNICITY");
            case "birthplace" -> List.of("BIRTHPLACE");
            case "location" -> Arrays.asList("ADDRESS", "CITY", "STATE", "ZIP");
            default -> List.of();
        };

        for (String columnName : searchableColumns) {
            List<String> matchingResults;
            try {
                matchingResults = dataFrame.searchByColumnValue(columnName, keyword);
            } catch (IllegalArgumentException e) {
                // skip column if doesn't exist
                matchingResults = new ArrayList<>();
            }

            for (String result : matchingResults) {
                if (!searchResults.contains(result)) {
                    searchResults.add(result);
                }
            }
        }

        return searchResults;
    }

    private List<String> getPatientNames() {
        List<String> patientNames = new ArrayList<>();

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            String fullName = dataFrame.getFullName(i);
            patientNames.add(fullName);
        }

        return patientNames;
    }

    private List<String> sortPatientNames(List<String> patientNames, String sortBy, boolean reverseOrder) {
        if (Objects.equals(sortBy, "first-name")) {
            patientNames.sort(new FirstNameComparator(dataFrame));
        } else if (Objects.equals(sortBy, "last-name")) {
            patientNames.sort(new LastNameComparator(dataFrame));
        } else if (Objects.equals(sortBy, "age")) {
            patientNames.sort(new AgeComparator(dataFrame));
        }

        if (reverseOrder) {
            Collections.reverse(patientNames);
        }

        return patientNames;
    }

    public Map<String, List<String>> getSortedGroupedPatientNames(String groupBy, String sortBy, boolean reverseOrder) {
        List<String> allPatientNames = getPatientNames();
        List<String> sortedPatientNames = sortPatientNames(allPatientNames, sortBy, reverseOrder);

        if (groupBy == null || groupBy.equals("none")) {
            // no grouping
            return Collections.singletonMap("", sortedPatientNames);
        }

        // apply grouping
        return groupPatientNames(sortedPatientNames, groupBy);
    }

    private Map<String, List<String>> groupPatientNames(List<String> sortedPatientNames, String groupBy) {
        Map<String, List<String>> groupedPatients = new HashMap<>();

        for (String patientName : sortedPatientNames) {
            int patientIndex = dataFrame.getRowIndexFromFullName(patientName);
            String groupKey = getGroupKey(groupBy, patientIndex);

            if (groupKey != null) {
                groupedPatients.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(patientName);
            }
        }

        return groupedPatients;
    }

    private String getGroupKey(String groupBy, int patientRow) {
        return switch (groupBy) {
            case "gender" -> dataFrame.getValue("GENDER", patientRow);
            case "city" -> dataFrame.getValue("CITY", patientRow);
            case "state" -> dataFrame.getValue("STATE", patientRow);
            case "birthplace" -> dataFrame.getValue("BIRTHPLACE", patientRow);
            case "race" -> dataFrame.getValue("RACE", patientRow);
            case "ethnicity" -> dataFrame.getValue("ETHNICITY", patientRow);
            case null, default -> null;
        };
    }

    private record FirstNameComparator(DataFrame dataFrame) implements Comparator<String> {
        @Override
        public int compare(String patientName1, String patientName2) {
            String firstName1 = dataFrame.getValueFromPatientName(patientName1, "FIRST");
            String firstName2 = dataFrame.getValueFromPatientName(patientName2, "FIRST");
            return firstName1.compareToIgnoreCase(firstName2);
        }
    }

    private record LastNameComparator(DataFrame dataFrame) implements Comparator<String> {
        @Override
        public int compare(String patientName1, String patientName2) {
            String lastName1 = dataFrame.getValueFromPatientName(patientName1, "LAST");
            String lastName2 = dataFrame.getValueFromPatientName(patientName2, "LAST");

            return lastName1.compareToIgnoreCase(lastName2);
        }
    }

    private record AgeComparator(DataFrame dataFrame) implements Comparator<String> {
        @Override
        public int compare(String patientName1, String patientName2) {
            String strDateOfBirth1 = dataFrame.getValueFromPatientName(patientName1, "BIRTHDATE");
            String strDateOfBirth2 = dataFrame.getValueFromPatientName(patientName2, "BIRTHDATE");

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfBirth1 = LocalDate.parse(strDateOfBirth1, formatter);
                LocalDate dateOfBirth2 = LocalDate.parse(strDateOfBirth2, formatter);

                return dateOfBirth1.compareTo(dateOfBirth2);
            } catch (NumberFormatException ex) {
                return 0;
            }
        }
    }

    public void deletePatient(String patientId) {
        int rowToDelete = -1;

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            if (dataFrame.getValue("ID", i).equals(patientId)) {
                rowToDelete = i;
                break;
            }
        }

        if (rowToDelete != -1) {
            dataFrame.deleteRow(rowToDelete);
            CSVWriter csvWriter = new CSVWriter(dataFrame, ModelFactory.CSV_FILE_PATH);
            csvWriter.writePatientDataToCSV();
        }
    }

    public void addPatient(Map<String, String> patientData) {
        dataFrame.addRow(patientData);
        CSVWriter csvWriter = new CSVWriter(dataFrame, ModelFactory.CSV_FILE_PATH);
        csvWriter.writePatientDataToCSV();
    }

    public void editPatient(Map<String, String> patientData) {
        String patientId = patientData.get("ID");
        int rowToEdit = -1;

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            if (dataFrame.getValue("ID", i).equals(patientId)) {
                rowToEdit = i;
                break;
            }
        }

        if (rowToEdit != -1) {
            System.out.println("Editing patient with ID: " + patientId);
            dataFrame.editRow(rowToEdit, patientData);
            CSVWriter csvWriter = new CSVWriter(dataFrame, ModelFactory.CSV_FILE_PATH);
            csvWriter.writePatientDataToCSV();
        }
    }

}
