package DataOperations;

import java.util.List;
import Logger.Informer;
import Validation.RowExistValidator;
import Validation.SchemaMatchValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
import models.Table;

public class UpdateOperation extends AbstractOperation {
    private final List<Row> rows;
    private final List<Row> newRows;
    private final Informer informer;

    public UpdateOperation(DataBase database, String tableName, List<Row> rows, List<Row> newRows) {
        super(database, tableName);
        this.rows = rows;
        this.newRows = newRows;
        this.informer = Informer.getInformer();
        this.validators.add(new TableMustExistValidator(tableName, database));
        this.validators.add(new SchemaMatchValidator(tableName, newRows, database));
        this.validators.add(new RowExistValidator(tableName, rows, database));
    }

    @Override
    public List<Row> run() {
        validate();
        return execute();
    }

    @Override
    public List<Row> execute() {
        List<Row> updatedRows = new java.util.ArrayList<>();
        Table table = database.getTable(tableName);
        for (Row row : rows) {
            int index = table.getRows().indexOf(row);
            int oldIndex = rows.indexOf(row);
            int newIndex = newRows.indexOf(newRows.get(oldIndex));
            if (newIndex >= 0) {
                table.getRows().set(index, newRows.get(newIndex));
                updatedRows.add(newRows.get(newIndex));
            }
        }
        informer.notifyObservers("Updating rows into table: " + tableName);
        return updatedRows;
    }

    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
    }
}