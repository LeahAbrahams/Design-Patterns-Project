package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataOperations.CreateTable;

// Singleton מייצר תלות גלובלית → פחות נקי לבדיקה (Testing).
public class DataBase {

    private String name;
    private Map<String, Table> tables = new HashMap<>();

    public DataBase(String name) {
        this.name = name;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void setTables(Map<String, Table> tables) {
        this.tables.clear();
        this.tables.putAll(tables);
    }

    public List<Row> insert(String tableName, List<Row> rows) {
        Table table = tables.get(tableName);
        List<Row> insertedRows = new java.util.ArrayList<>();
        for (Row row : rows) {
            if (table.addRow(row)) {
                insertedRows.add(row);
            }
        }
        return insertedRows;

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
public  List<Row>updateTable(String tableName, List<Row> rows, List<Row> newRows) {
        return new DataOperations.UpdateOperation(this, tableName, rows, newRows).run();
    }
    
    public List<Row> insertIntoTable(String tableName, List<Row> rows)
    {
       return new DataOperations.InsertOperation(this, tableName, rows).run();
    }
    public List<Row> quary(String condition,String tableName)
    {
      return new DataOperations.QueryOperation(this, tableName, condition).run();

    }
    public  List<Row> deleteFromTable(String tableName,List<Row>todelete)
    {
        return new DataOperations.DeleteOperation(this, tableName,todelete).run();



    }
    public List<Row> removeTable(String tableName){

        return new DataOperations.RemoveTable(tableName,this).run();
    }

    // public void removeTable(String tableName) {
    //     if (tables.remove(tableName) != null) {
    //         System.out.println("Table " + tableName + " was removed from database " + name);
    //     }
    // }
}
