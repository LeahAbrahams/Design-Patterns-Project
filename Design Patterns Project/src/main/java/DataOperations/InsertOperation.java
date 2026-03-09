package DataOperations;

import java.util.List;
import Logger.Informer;
import Validation.SchemaMatchValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;

public class InsertOperation extends AbstractOperation {
    private final List<Row> rows;
    private final Informer informer;

    public InsertOperation(DataBase database, String tableName, List<Row> rows) {
        super(database, tableName);
        this.rows = rows;
        this.informer = Informer.getInformer();
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
        informer.notifyObservers("Inserting rows into table: " + tableName);
        return database.insert(tableName, rows);
    }

    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
    }
}
