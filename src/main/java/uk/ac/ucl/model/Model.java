package uk.ac.ucl.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Model
{
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

  public List<String> searchFor(String keyword)
  {
    List<String> searchResults = new ArrayList<>();
    List<String> searchableColumns = Arrays.asList("FIRST", "LAST"); // searches in these columns
    for (String columnName : searchableColumns) {
      List<String> matchingResults = dataFrame.searchByColumnValue(columnName, keyword);

      for (String result : matchingResults) { // merges search results
        if (!searchResults.contains(result)) {
          searchResults.add(result);
        }
      }
    }

    return searchResults;

  }

}
