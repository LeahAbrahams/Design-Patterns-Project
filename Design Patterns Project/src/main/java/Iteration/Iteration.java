package Iteration;

import java.util.List;

import models.Row;

public class Iteration {
    private final Iiteration byIteration;

    public Iteration(Iiteration byIteration) {
        this.byIteration = byIteration;
    }

    public void iteration(List<Row> rows) {
        byIteration.iterate(rows);
    }
}