
package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataOperations.CreateTable;

// Singleton מייצר תלות גלובלית → פחות נקי לבדיקה (Testing).


public class DataBase {


    private  String name;
private  Map<String, Table> tables = new HashMap<>();

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
    public List<Row> insertIntoTable(String tableName,List<Row> rows) {
        Table table = tables.get(tableName);
        List<Row> insertedRows = new java.util.ArrayList<>();
        for (Row row : rows) {
           if( table.addRow(row))
           insertedRows.add(row);
        }
        return insertedRows;
        
    }
    public void setName(String name) {
       this.name = name;
    }
    public List<Row> createTable(String tableName, List<Column> columns) {
       return new CreateTable(columns, tableName,this).run();
       
    }
       public  Table addTable(Table table) {
        tables.put(table.getName(), table);
        return table;
    } 

    public void removeTable(String tableName) {
        if (tables.remove(tableName) != null) {
            System.out.println("Table " + tableName + " was removed from database " + name);
        } 
    }
}
