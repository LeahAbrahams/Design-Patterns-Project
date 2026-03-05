package DataOperations;

import java.util.ArrayList;
import java.util.List;

import Validation.RowExistValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
import models.Table;

public class DeleteOperation extends AbstractOperation {
    private List<Row> rowsToDelete;
    List<Validator> validators = new ArrayList<>();

    public DeleteOperation(DataBase database, String tableName, List<Row> rowsToDelete) {
        super(database, tableName);
        this.rowsToDelete = rowsToDelete;
        validators.add(new TableMustExistValidator(tableName, database));
        validators.add(new RowExistValidator(tableName, rowsToDelete, database));
    }

    @Override
    public List<Row> run() {
        this.validate();
        return this.execute();
    }

    @Override
    public List<Row> execute() {
        Table table = database.getTables().get(tableName);
        List<Row> deletedRows = new ArrayList<>();
        for (Row row : rowsToDelete) {
            if (table.getRows().remove(row)) {
                deletedRows.add(row);
            }
        }
        return deletedRows;
    }

    @Override
    public void validate() {
        for (Validator validatorn : validators) {
            validatorn.validator();
        }
    }
}