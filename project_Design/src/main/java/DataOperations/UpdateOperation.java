
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOperations;
import java.util.List;

import Validation.RowExistValidator;
import Validation.SchemaMatchValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
import models.Table;

public class UpdateOperation extends AbstractOperation {
    private  final List<Row> rows;
    private final  List<Row> newRows;  
    
    private final  List<Validator> validators=new java.util.ArrayList<>();

public UpdateOperation(DataBase database, String tableName,List<Row> rows,List<Row> newRows) {
        super(database, tableName);
        this.rows = rows;
        this.newRows = newRows;
        
        this.validators.add(new TableMustExistValidator(tableName, database));
        this.validators.add(new SchemaMatchValidator(tableName, newRows, database));
            this.validators.add(new RowExistValidator(tableName, rows, database)); // Assuming all rows have the same schema
}
@Override
    public List<Row> run() {
        validate();
        return execute();
    }

    @Override
    public List<Row> execute() {
        List<Row> updatedRows = new java.util.ArrayList<>();
        Table table = database.getTables().get(tableName);
       for(Row row:rows){
           int index=table.getRows().indexOf(row);
           int oldIndex=rows.indexOf(row);
        int newIndex=newRows.indexOf(newRows.get(oldIndex));
           if(newIndex>=0){
        table.getRows().set(index, newRows.get(newIndex));
        updatedRows.add(newRows.get(newIndex));

           }
       }
       return updatedRows;
    }

    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
       
    }



}
