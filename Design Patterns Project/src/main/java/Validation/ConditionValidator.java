package Validation;

import Condition.Condition;
import models.DataBase;
import models.Schema;

public class ConditionValidator implements Validator {
    private final Condition condition;
    private final Schema schema;

    public ConditionValidator(Condition condition, DataBase dataBase, String tableName) {
        this.condition = condition;
        this.schema = dataBase.getTable(tableName).getSchema();
    }

    @Override
    public void validator() {
        condition.validate(schema);
    }
}
