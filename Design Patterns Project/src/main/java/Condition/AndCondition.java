package Condition;

import java.util.ArrayList;
import java.util.List;

import models.Row;
import models.Schema;

public class AndCondition implements Condition {
    private List<Condition> conditions = new ArrayList<>();

    public AndCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public void validate(Schema schema) {
        for (Condition condition : conditions) {
            condition.validate(schema);
        }
    }

    @Override
    public boolean evaluate(Row row) {
        for (Condition condition : conditions) {
            if (!condition.evaluate(row)) {
                return false;
            }
        }
        return true;
    }
}
