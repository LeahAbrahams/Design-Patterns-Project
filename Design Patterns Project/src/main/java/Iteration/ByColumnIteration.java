package Iteration;

import java.util.ArrayList;
import java.util.List;
import models.Row;

public class ByColumnIteration implements Iiteration {
    private final String column;

    public ByColumnIteration(String column) {
        this.column = column;
    }

    @Override
    public List<Row> iterate(List<Row> rows) {
        List<Row> sortedRows = new ArrayList<>(rows);
        sortedRows.sort((r1, r2) -> {
            Object rawValue1 = r1.getValue(column);
            Object rawValue2 = r2.getValue(column);

            if (rawValue1 == null && rawValue2 == null) {
                return 0;
            }
            if (rawValue1 == null) {
                return -1;
            }
            if (rawValue2 == null) {
                return 1;
            }
            if (!(rawValue1 instanceof Comparable) || !(rawValue2 instanceof Comparable)) {
                throw new IllegalArgumentException("Column " + column + " contains non-comparable values");
            }

            Comparable value1 = (Comparable) rawValue1;
            Comparable value2 = (Comparable) rawValue2;
            return value1.compareTo(value2);
        });
        return sortedRows;
    }
}
