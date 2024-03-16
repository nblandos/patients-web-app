package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private final String name;
    private final List<String> rows;

    public Column(String name) {
        this.name = name;
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return rows.size();
    }

    public String getRowValue(int index) {
        if (index >= 0 && index < rows.size()) {
            return rows.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void setRowValue(int index, String value) {
        if (index >= 0 && index < rows.size()) {
            rows.set(index, value);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void addRowValue(String value) {
        rows.add(value);
    }
}

