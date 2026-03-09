package Iteration;

import java.util.ArrayList;
import java.util.List;
import models.Row;

public class ByIncomeIteration implements Iiteration {
    @Override
    public List<Row> iterate(List<Row> rows) {
        return new ArrayList<>(rows);
    }
}
