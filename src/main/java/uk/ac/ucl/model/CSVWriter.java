package uk.ac.ucl.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    private final String csvFilePath;

    public CSVWriter(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public void writePatientDataToCSV(DataFrame dataFrame) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(String.join(",", dataFrame.getColumnNames()));
            writer.newLine();

            for (int i = 0; i < dataFrame.getRowCount(); i++) {
                List<String> rowValues = new ArrayList<>();
                for (String columnName : dataFrame.getColumnNames()) {
                    rowValues.add(dataFrame.getValue(columnName, i));
                }
                writer.write(String.join(",", rowValues));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}