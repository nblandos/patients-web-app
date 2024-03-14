package uk.ac.ucl.model;

import java.io.FileNotFoundException;
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
        Reader reader = null;

        try {
            reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
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
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            // return default data frame here
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close file reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataFrame;
    }
}
