package builder;

import models.Column;
import models.DataType;
import models.Table;

public class TableBuilder implements Builder {
    private final Table table;

    public TableBuilder() {
        this.table = new Table();
    }

    @Override
    public void build(String name, java.util.List<Column> columns) {
        setName(name);
        for (Column column : columns) {
            addColumn(column.getName(), column.getDataType());
        }
    }

    @Override
    public void setName(String name) {
        table.setName(name);
    }

    @Override
    public void addColumn(String columnName, DataType dataType) {
        Column column = new Column(columnName, dataType);
        table.addColumn(column);
    }

    @Override
    public Table getTable() {
        return table;
    }
}