package Iteration;

import Condition.Condition;
import java.util.ArrayList;
import java.util.List;
import models.Row;
import models.Schema;

public class ByConditionIteration implements Iiteration {
    private final Schema schema;
    private final Condition condition;

    public ByConditionIteration(Condition condition, Schema schema) {
        this.schema = schema;
        this.condition = condition;
    }

    @Override
    public List<Row> iterate(List<Row> rows) {
        condition.validate(schema);
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if (condition.evaluate(row)) {
                result.add(row);
            }
        }
        return result;
    }
}
