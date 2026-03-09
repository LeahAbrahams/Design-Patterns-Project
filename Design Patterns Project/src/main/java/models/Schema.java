package models;

import java.util.List;

public class Schema {
    private final List<Column> columns = new java.util.ArrayList<>();

    public java.util.List<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }
}