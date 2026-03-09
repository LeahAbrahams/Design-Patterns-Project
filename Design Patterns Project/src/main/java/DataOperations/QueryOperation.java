package DataOperations;

import java.util.List;
import Condition.Condition;
import Validation.ConditionValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.DataBase;
import models.Row;

public class QueryOperation extends AbstractOperation {
    private final Condition condition;

    public QueryOperation(DataBase database, String tableName, Condition condition) {
        super(database, tableName);
        this.condition = condition;
        this.validators.add(new TableMustExistValidator(tableName, database));
        this.validators.add(new ConditionValidator(condition, database, tableName));
    }

    @Override
    public List<Row> run() {
        validate();
        return execute();
    }

    @Override
    public List<Row> execute() {
        List<Row> rows = database.getTable(tableName).getRows();
        List<Row> result = new java.util.ArrayList<>();
        for (Row row : rows) {
            if (condition.evaluate(row)) {
                result.add(row);
            }
        }
        return result;
    }

    @Override
    public void validate() {
        for (Validator validator : validators) {
            validator.validator();
        }
    }
}
