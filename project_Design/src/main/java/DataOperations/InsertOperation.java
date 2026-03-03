
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOperations;
import java.util.List;

import Validation.SchemaMatchValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
/**
 *
 * @author ברכי
 */
public class InsertOperation extends AbstractOperation {
    private final DataBase database;
    private final String tableName; 
    private final List<Row> rows;
    private final List<Validator> validators=new java.util.ArrayList<>();
    public InsertOperation(DataBase database, String tableName, List<Row> rows) {
        this.database = database;
        this.tableName = tableName;
        this.rows = rows;
        this.validators.add(new TableMustExistValidator(tableName, database));
       this.validators.add(new SchemaMatchValidator(tableName, rows, database));
    }
    @Override
    public List<Row> run() {
       validate();
       return execute();
    }
@Override
    public List<Row> execute() {
        
        return database.insertIntoTable(tableName, rows);
    }
    @Override
    public void validate() {
    
         for (Validator validator : validators) {
            validator.validator();
        }

    }
   
}
