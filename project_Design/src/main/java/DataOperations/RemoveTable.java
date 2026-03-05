
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOperations;
import java.util.List;

import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
/**
 *
 * @author ברכי
 */
public class RemoveTable extends AbstractOperation {

 
 
    private final  List<Validator> validators=new java.util.ArrayList<>();   
        public RemoveTable( String tableName,DataBase database) {
        super(database, tableName);
        this.validators.add(new TableMustExistValidator(tableName, database));
       
    }
    @Override
    public List<Row> run() {
        this.validate();
        return this.execute();
    }
    @Override
   public List<Row> execute() {
         database.getTables().remove(tableName);
         return List.of();
       
       
   } 
    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
    }
}


    

