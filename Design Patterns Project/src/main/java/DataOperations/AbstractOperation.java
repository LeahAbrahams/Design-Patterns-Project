package DataOperations;

import java.util.List;

import models.DataBase;
import models.Row;

public abstract class AbstractOperation {
    public abstract List<Row> run();
    public abstract void validate();
    public abstract List<Row> execute();
    protected final DataBase database;
    protected final String tableName;

    public AbstractOperation(DataBase database, String tableName) {
        this.database = database;
        this.tableName = tableName;
    }
}
