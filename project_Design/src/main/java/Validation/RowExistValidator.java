/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validation;
import java.util.List;

import models.DataBase;
import models.Row;
import models.Table;
/**
 *
 * @author ברכי
 */
public class RowExistValidator implements Validator {
    private final String tableName;
    private final List<Row> rows;
    private final DataBase database;

    public RowExistValidator(String tableName, List<Row> rows, DataBase database) {
        this.tableName = tableName;
        this.rows = rows;
        this.database = database;
    }

    @Override
    public void validator() {
        Table table = database.getTables().get(tableName);
        
        for (Row row : rows) {
            if (table.getRows().indexOf(row) < 0) {
                throw new RuntimeException("Row does not exist in the table: " + row);
            }
        }
    }
    
}
