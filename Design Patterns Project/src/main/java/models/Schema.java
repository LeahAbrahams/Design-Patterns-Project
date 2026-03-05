package models;

import java.util.List;

public class Schema implements Cloneable {
    private final List<Column> columns = new java.util.ArrayList<>();

    public java.util.List<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
        System.out.println("Column " + column.getName() + " was added to schema");
    }

    public void removeColumn(String columnName) {
        boolean removed = columns.removeIf(column -> column.getName().equals(columnName));
        if (removed) {
            System.out.println("Column " + columnName + " was removed from schema");
        } else {
            System.out.println("Column " + columnName + " not found in schema");
        }
    }

    @Override
    public Schema clone() {
        Schema clonedSchema = new Schema();
        for (Column column : this.columns) {
            clonedSchema.columns.add(column.clone());
        }
        return clonedSchema;
    }
}