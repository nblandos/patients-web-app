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
                if (!value.isEmpty()) {
                    // checks if search string contains column value or column value contains search string
                    if (value.toLowerCase().contains(keyword.toLowerCase()) || keyword.toLowerCase().contains(value.toLowerCase())) {
                        matchingRows.add(getFullName(row));
                    }
                }
            }
            return matchingRows;
        } else {
            throw new IllegalArgumentException("Column '" + columnName + "' does not exist");
        }
    }

    public String getFullName(int row) {
        String firstName = "";
        String lastName = "";

        try {
            firstName = getValue("FIRST", row);
        } catch (IllegalArgumentException ignored) {
        }

        try {
            lastName = getValue("LAST", row);
        } catch (IllegalArgumentException ignored) {
        }

        if (firstName.isEmpty() && lastName.isEmpty()) {
            return "N/A"; // neither column exists
        } else if (firstName.isEmpty()) {
            return lastName; // only "LAST" column exists
        } else if (lastName.isEmpty()) {
            return firstName; // only "FIRST" column exists
        } else {
            return firstName + " " + lastName; // both exist
        }
    }

    public int getRowIndexFromFullName(String fullName) {
        int rowIndex = -1;
        for (int i = 0; i < getRowCount(); i++) {
            if (getFullName(i).equals(fullName) && !fullName.equals("N/A")) {
                rowIndex = i;
                break;
            }
        }
        return rowIndex;
    }
}
