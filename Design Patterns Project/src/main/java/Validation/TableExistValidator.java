package Validation;

import models.DataBase;

public class TableExistValidator implements Validator {
    DataBase dataBase;
    String tableName;

    public TableExistValidator(DataBase database, String tableName) {
        this.dataBase = database;
        this.tableName = tableName;
    }

    @Override
    public void validator() {
        if (dataBase.getTables().containsKey(tableName)) {
            throw new IllegalArgumentException("Table " + tableName + " already exists in the database.");
        }
    }
}