package uk.ac.ucl.model;

import java.util.*;

public class Model
{
  // TODO: in display patient list add filters to sort by age/location
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

  public List<String> getPatientNames()
  {
    List<String> patientNames = new ArrayList<>();

    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      String fullName = dataFrame.getFullName(i);
      patientNames.add(fullName);
    }

    return patientNames;
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

}
