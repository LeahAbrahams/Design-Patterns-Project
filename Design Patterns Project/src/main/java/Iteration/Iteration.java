package Iteration;

import java.util.List;
import models.Row;

public class Iteration {
    private Iiteration strategy;

    public Iteration(Iiteration strategy) {
        this.strategy = strategy;
    }

    public List<Row> iterate(List<Row> rows) {
        return strategy.iterate(rows);
    }

    public void setStrategy(Iiteration strategy) {
        this.strategy = strategy;
    }
}
