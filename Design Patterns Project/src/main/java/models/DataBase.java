package models;

import Condition.Condition;
import DataOperations.CreateTable;
import Iteration.Iiteration;
import Iteration.Iteration;
import Logger.LogManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    private String name;
    private final Map<String, Table> tables = new HashMap<>();
    private final LogManager logManager;

    public DataBase(String name) {
        this.name = name;
        this.logManager = LogManager.getInstance();
    }

    public String getName() {
        return name;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> createTable(String tableName, List<Column> columns) {
        return new CreateTable(columns, tableName, this).run();
    }

    public Table addTable(Table table) {
        tables.put(table.getName(), table);
        return table;
    }

    public List<Row> insert(String tableName, List<Row> rows) {
        Table table = getTable(tableName);
        List<Row> insertedRows = new java.util.ArrayList<>();
        for (Row row : rows) {
            if (table.addRow(row)) {
                insertedRows.add(row);
            }
        }
        return insertedRows;
    }

    public List<Row> insertIntoTable(String tableName, List<Row> rows) {
        return new DataOperations.InsertOperation(this, tableName, rows).run();
    }

    public List<Row> updateTable(String tableName, List<Row> rows, List<Row> newRows) {
        return new DataOperations.UpdateOperation(this, tableName, rows, newRows).run();
    }

    public List<Row> query(Condition condition, String tableName) {
        return new DataOperations.QueryOperation(this, tableName, condition).run();
    }

    public List<Row> deleteFromTable(String tableName, List<Row> toDelete) {
        return new DataOperations.DeleteOperation(this, tableName, toDelete).run();
    }

    public List<Row> removeTable(String tableName) {
        return new DataOperations.RemoveTable(tableName, this).run();
    }

    public List<Row> iterate(String tableName, Iiteration strategy) {
        Table table = getTable(tableName);
        if (table == null) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist in database");
        }

        Iteration iteration = new Iteration(strategy);
        return iteration.iterate(table.getRows());
    }
}
