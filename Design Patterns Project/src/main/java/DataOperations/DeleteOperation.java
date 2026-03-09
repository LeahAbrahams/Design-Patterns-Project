package DataOperations;

import java.util.ArrayList;
import java.util.List;
import Logger.Informer;
import Validation.RowExistValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;
import models.Table;

public class DeleteOperation extends AbstractOperation {
    private final List<Row> rowsToDelete;
    private final Informer informer;

    public DeleteOperation(DataBase database, String tableName, List<Row> rowsToDelete) {
        super(database, tableName);
        this.rowsToDelete = rowsToDelete;
        this.informer = Informer.getInformer();
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
        Table table = database.getTable(tableName);
        List<Row> deletedRows = new ArrayList<>();
        for (Row row : rowsToDelete) {
            if (table.getRows().remove(row)) {
                deletedRows.add(row);
            }
        }
        informer.notifyObservers("Deleted " + deletedRows.size() + " rows from " + tableName);
        return deletedRows;
    }

    @Override
    public void validate() {
        for (Validator validatorn : validators) {
            validatorn.validator();
        }
    }
}