package uk.ac.ucl.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataLoader {

    private String filePath;

    public DataLoader(String filePath) {
        this.filePath = filePath;
    }

    public DataFrame loadData() {
        DataFrame dataFrame = new DataFrame();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()))
        {
            String[] columnNames = csvParser.getHeaderMap().keySet().toArray(new String[0]);
            for (String columnName : columnNames) {
                dataFrame.addColumn(columnName);
            }

            for (CSVRecord csvRecord : csvParser)
            {
                for (String columnName : columnNames) {
                    dataFrame.addValue(columnName, csvRecord.get(columnName));
                }
            }
        } catch (IOException e)
        {
            // Handle opening file if it doesn't exist, program shouldn't crash
            e.printStackTrace();
        }

        return dataFrame;
    }
}
