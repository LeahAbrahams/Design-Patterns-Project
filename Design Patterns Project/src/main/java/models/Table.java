package models;

import Clone.TableClone;

public class Table implements TableClone {
    private String name;
    private Schema schema;
    private final java.util.List<Row> rows = new java.util.ArrayList<>();

    public Table() {
        this.schema = new Schema();
    }

    public Table(Table table) {
        this();
        this.name = table.name;

        for (Column column : table.getSchema().getColumns()) {
            this.schema.addColumn(new Column(column.getName(), column.getDataType()));
        }

        for (Row row : table.getRows()) {
            this.rows.add(new Row(row.getValues()));
        }
    }

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
        rows.remove(row);
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Table clone() throws CloneNotSupportedException {
        return new Table(this);
    }
}
