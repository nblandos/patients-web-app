package uk.ac.ucl.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DataFrame {
    private Map<String, Column> columns;

    public DataFrame() {
        columns = new HashMap<>();
    }

    public void addColumn(String columnName) {
        columns.put(columnName, new Column(columnName));
    }

    public List<String> getColumnNames() {
        return new ArrayList<>(columns.keySet());
    }

    public int getRowCount() {
        if (!columns.isEmpty()) {
            for (Column column : columns.values()) {
                return column.getSize();
            }
        }
        return 0;
    }

    public String getValue(String columnName, int row) {
        Column column = columns.get(columnName);
        if (column != null) {
            return column.getRowValue(row);
        } else {
            throw new IllegalArgumentException("Column '" + columnName + "' does not exist");
        }
    }

    public void putValue(String columnName, int row, String value) {
        Column column = columns.get(columnName);
        if (column != null) {
            column.setRowValue(row, value);
        } else {
            throw new IllegalArgumentException("Column '" + columnName + "' does not exist");
        }
    }

    public void addValue(String columnName, String value) {
        Column column = columns.get(columnName);
        if (column != null) {
            column.addRowValue(value);
        } else {
            throw new IllegalArgumentException("Column '" + columnName + "' does not exist");
        }
    }

    public List<String> searchByColumnValue(String columnName, String keyword) {
        Column column = columns.get(columnName);
        if (column != null) {
            List<String> matchingRows = new ArrayList<>();
            for (int row = 0; row < column.getSize(); row++) {
                String value = column.getRowValue(row);
                if (value.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingRows.add(getFullName(row));
                }
            }
            return matchingRows;
        }
        else {
            throw new IllegalArgumentException("Column '" + columnName + "' does not exist");
        }
    }

    public String getFullName(int row) {
        String firstName = getValue("FIRST", row);
        String lastName = getValue("LAST", row);
        return firstName + " " + lastName;
    }

    public int getRowIndexFromFullName(String fullName) {
        int rowIndex = -1;
        for (int i = 0; i < getRowCount(); i++) {
            if (getFullName(i).equals(fullName)) {
                rowIndex = i;
                break;
            }
        }
        return rowIndex;
    }
}
