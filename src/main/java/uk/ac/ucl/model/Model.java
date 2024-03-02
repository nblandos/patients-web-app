package uk.ac.ucl.model;

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
      String firstName = dataFrame.getValue("FIRST", i);
      String lastName = dataFrame.getValue("LAST", i);
      String fullName = firstName + " " + lastName;
      patientNames.add(fullName);
    }

    return patientNames;
  }

  public List<String> searchFor(String keyword)
  {
    // TODO
    return new ArrayList<>();
//    return dataFrame.searchByColumnValue("PatientName", keyword);
  }
}
