package Iteration;

import java.util.ArrayList;
import java.util.List;

import models.Row;

public class ByColumnIteration implements Iiteration {
    private String column;

    public ByColumnIteration(String column) {
        this.column = column;
    }

    @Override
    public List<Row> iterate(List<Row> rows) {
        List<Row> filteredRows = new ArrayList<>();
        filteredRows = rows;
        filteredRows.sort((r1, r2) -> {
            Comparable v1 = (Comparable) r1.getValue(column);
            Comparable v2 = (Comparable) r2.getValue(column);
            return v1.compareTo(v2);
        });
        return filteredRows;
    }
}