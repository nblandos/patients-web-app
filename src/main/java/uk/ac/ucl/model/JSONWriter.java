package uk.ac.ucl.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONWriter {
    private DataFrame dataFrame;
    private String filePath;

    public JSONWriter(DataFrame dataFrame, String csvFilePath) {
        this.dataFrame = dataFrame;
        this.filePath = csvFilePath.replace(".csv", ".json");
    }

    public void writeDataToJSON() {
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 0; i < dataFrame.getRowCount(); i++) {
            Map<String, String> row = new HashMap<>();
            for (String columnName : dataFrame.getColumnNames()) {
                row.put(columnName, dataFrame.getValue(columnName, i));
            }
            data.add(row);
        }

        Map<String, List<Map<String, String>>> container = new HashMap<>();
        container.put("patients", data);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), container);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}