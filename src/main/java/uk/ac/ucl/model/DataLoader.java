package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataLoader {

    private static final String[] DEFAULT_COLUMN_NAMES = {
            "ID", "BIRTHDATE", "DEATHDATE", "SSN", "DRIVERS", "PASSPORT", "PREFIX", "FIRST", "LAST",
            "SUFFIX", "MAIDEN", "MARITAL", "RACE", "ETHNICITY", "GENDER", "BIRTHPLACE", "ADDRESS", "CITY", "STATE", "ZIP"
    };
    private final String filePath;

    public DataLoader(String filePath) {
        this.filePath = filePath;
    }

    public DataFrame loadData() {
        DataFrame dataFrame = new DataFrame();

        try (Reader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            String[] columnNames = csvParser.getHeaderMap().keySet().toArray(new String[0]);
            for (String columnName : columnNames) {
                dataFrame.addColumn(columnName);
            }
            for (CSVRecord csvRecord : csvParser) {
                for (String columnName : columnNames) {
                    dataFrame.addValue(columnName, csvRecord.get(columnName));
                }
            }
        }
        catch (IOException e) {
            System.err.println("IOException: " + filePath);
            // if IOException, return empty data frame with default column names
            for (String columnName : DEFAULT_COLUMN_NAMES) {
                dataFrame.addColumn(columnName);
            }
        }
        return dataFrame;
    }
}
