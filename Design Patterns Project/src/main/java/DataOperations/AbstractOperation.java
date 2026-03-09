package DataOperations;

import java.util.List;
import Validation.Validator;
import models.DataBase;
import models.Row;

public abstract class AbstractOperation {
    protected List<Validator> validators;
    protected final DataBase database;
    protected final String tableName;

    public abstract List<Row> run();
    public abstract void validate();
    public abstract List<Row> execute();

    public AbstractOperation(DataBase database, String tableName) {
        this.validators = new java.util.ArrayList<>();
        this.database = database;
        this.tableName = tableName;
    }
}
