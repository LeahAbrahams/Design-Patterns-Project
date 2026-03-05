
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOperations;

import java.util.List;

import Validation.TableExistValidator;
import Validation.Validator;
import builder.TableBuilder;
import models.Column;
import models.DataBase;
import models.Row;

/**
 *
 * @author ברכי
 */
public class CreateTable extends AbstractOperation {

    private final List<Column> columns;
   
    private final List<Validator> validators = new java.util.ArrayList<>();

    public CreateTable(List<Column> columns, String tableName, DataBase database) {
        super(database, tableName);
        this.columns = columns;
      
        this.validators.add(new TableExistValidator(database, tableName));

    }

    @Override
    public List<Row> run() {
        this.validate();
        return this.execute();
    }

    @Override
    public List<Row> execute() {
        TableBuilder builder = new TableBuilder();
        builder.build(tableName, columns);
        return database.addTable(builder.getTable()).getRows();

    }

    @Override
    public void validate() {
        for (Validator validatorn : validators) {
            validatorn.validator();

        }

    }
}
