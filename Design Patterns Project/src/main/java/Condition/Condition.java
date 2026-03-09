package Condition;

import models.Row;
import models.Schema;

public interface Condition {
    boolean evaluate(Row row);
    void validate(Schema schema);
}
