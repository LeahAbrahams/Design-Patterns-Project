
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOperations;
import models.DataBase;
import models.Row;
import Validation.TableMustExistValidator;
import Validation.Validator;
import java.util.List;
/**
 *
 * @author ברכי
 */
public class RemoveTable extends AbstractOperation {

 
  private final  String tableName;
  private final  DataBase database;
    private final  List<Validator> validators=new java.util.ArrayList<>();   
        public RemoveTable( String tableName,DataBase database) {
        this.tableName = tableName;
        this.database = database;
        this.validators.add(new TableMustExistValidator(tableName, database));
       
    }
    @Override
    public List<Row> run() {
        this.validate();
        return this.execute();
    }
    @Override
   public List<Row> execute() {
         database.removeTable(tableName);
         return List.of();
       
       
   } 
    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
    }
}


    

