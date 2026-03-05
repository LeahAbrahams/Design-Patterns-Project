package models;

public class Table implements Cloneable {
    private String name;
    private Schema schema;
    private final java.util.List<Row> rows = new java.util.ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schema getSchema() {
        return schema;
    }

    public java.util.List<Row> getRows() {
        return rows;
    }

    public void addColumn(Column column) {
        schema.addColumn(column);
    }

    public boolean addRow(Row row) {
        return rows.add(row);
    }

    public void removeRow(Row row) {
        if (rows.remove(row)) {
            System.out.println("Row removed from table " + name);
        } else {
            System.out.println("Row not found in table " + name);
        }
    }

    @Override
    public Table clone() {
        Table clonedTable = new Table();
        clonedTable.name = this.name;
        clonedTable.schema = this.schema.clone();
        for (Row row : this.rows) {
            clonedTable.rows.add(row.clone());
        }
        return clonedTable;
    }
}
