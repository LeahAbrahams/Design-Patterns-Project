package models;

import Logger.IInformer;
import Logger.Informer;

public class Table implements Cloneable {
    private String name;
    private Schema schema;
    private final java.util.List<Row> rows = new java.util.ArrayList<>();
    private IInformer informer = new Informer();

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

    public IInformer getInformer() {
        return informer;
    }

    public void addColumn(Column column) {
        schema.addColumn(column);
    }

    public boolean addRow(Row row) {
        boolean result = rows.add(row);
        if (result) {
            informer.notifyObservers("INSERT: Row added to table '" + name + "'");
        }
        return result;
    }

    public void removeRow(Row row) {
        if (rows.remove(row)) {
            informer.notifyObservers("DELETE: Row removed from table '" + name + "'");
        }
    }

    public void updateRow(int index, Row newRow) {
        Row oldRow = rows.set(index, newRow);
        informer.notifyObservers("UPDATE: Row updated in table '" + name + "'");
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
