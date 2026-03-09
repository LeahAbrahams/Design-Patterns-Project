package DataOperations;

import java.util.List;

import Validation.TableExistValidator;
import Validation.Validator;
import builder.TableBuilder;
import models.Column;
import models.DataBase;
import models.Row;

public class CreateTable extends AbstractOperation {

    private final List<Column> columns;

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
        database.addTable(builder.build(tableName, columns));
        return List.of();
    }

    @Override
    public void validate() {
        for (Validator validatorn : validators) {
            validatorn.validator();
        }
    }
}
