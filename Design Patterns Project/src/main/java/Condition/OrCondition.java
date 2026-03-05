package Condition;

import java.util.ArrayList;
import java.util.List;

import models.Row;

public class OrCondition implements Condition {
    private List<Condition> conditions = new ArrayList<>();

    public OrCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean evaluate(Row row) {
        for (Condition condition : conditions) {
            if (condition.evaluate(row)) {
                return true;
            }
        }
        return false;
    }
}