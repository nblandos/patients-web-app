package uk.ac.ucl.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Model
{
  // TODO: group by location
  // TODO: requirements 8 & 9 possibly 10
  // TODO: use bootstrap and css to make it look nice

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

  public Map<String, String> getPatientDetails(String patientName) {
    Map<String, String> patientDetails = new HashMap<>();

    int rowIndex = dataFrame.getRowIndexFromFullName(patientName);

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

  public List<String> searchFor(String keyword, String searchColumn)
  {
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

  private List<String> getPatientNames()
  {
    List<String> patientNames = new ArrayList<>();

    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      String fullName = dataFrame.getFullName(i);
      patientNames.add(fullName);
    }

    return patientNames;
  }

  public List<String> getSortedPatientNames(String sortBy, boolean reverseOrder) {
    List<String> patientNames = getPatientNames();

    if (Objects.equals(sortBy, "first-name")) {
      Collections.sort(patientNames, new FirstNameComparator(dataFrame));
    } else if (Objects.equals(sortBy, "last-name")) {
      Collections.sort(patientNames, new LastNameComparator(dataFrame));
    } else if (Objects.equals(sortBy, "age")) {
      Collections.sort(patientNames, new AgeComparator(dataFrame));
    }

    if (reverseOrder) {
      Collections.reverse(patientNames);
    }

    return patientNames;
  }

  private record FirstNameComparator(DataFrame dataFrame) implements Comparator<String> {
    @Override
      public int compare(String patientName1, String patientName2) {
        int row1 = dataFrame.getRowIndexFromFullName(patientName1);
        int row2 = dataFrame.getRowIndexFromFullName(patientName2);

        String firstName1 = dataFrame.getValue("FIRST", row1);
        String firstName2 = dataFrame.getValue("FIRST", row2);

        return firstName1.compareToIgnoreCase(firstName2);
      }
    }

  private record LastNameComparator(DataFrame dataFrame) implements Comparator<String> {
    @Override
    public int compare(String patientName1, String patientName2) {
      int row1 = dataFrame.getRowIndexFromFullName(patientName1);
      int row2 = dataFrame.getRowIndexFromFullName(patientName2);

      String lastName1 = dataFrame.getValue("LAST", row1);
      String lastName2 = dataFrame.getValue("LAST", row2);

      return lastName1.compareToIgnoreCase(lastName2);
    }
  }

  private record AgeComparator(DataFrame dataFrame) implements Comparator<String> {
    @Override
      public int compare(String patientName1, String patientName2) {
        int row1 = dataFrame.getRowIndexFromFullName(patientName1);
        int row2 = dataFrame.getRowIndexFromFullName(patientName2);

        String strDateOfBirth1 = dataFrame.getValue("BIRTHDATE", row1);
        String strDateOfBirth2 = dataFrame.getValue("BIRTHDATE", row2);

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


}
