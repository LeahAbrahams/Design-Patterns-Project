package Validation;

import models.DataBase;

public class TableMustExistValidator implements Validator {
    private final String tableName;
    private final DataBase database;

    public TableMustExistValidator(String tableName, DataBase database) {
        this.tableName = tableName;
        this.database = database;
    }

    @Override
    public void validator() {
        if (!database.getTables().containsKey(tableName)) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist in database");
        }
    }
}
