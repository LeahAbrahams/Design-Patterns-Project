package Condition;

import models.Row;

public class SimpleCondition implements Condition {
    private String column;
    private Operator operator;
    private Object value;

    public SimpleCondition(String column, String operatorStr, Object value) {
        this.column = column;
        this.operator = parseOperator(operatorStr);
        this.value = value;
    }

    private Operator parseOperator(String op) {
        switch (op) {
            case "==":
                return Operator.EQUALS;
            case "!=":
                return Operator.NOT_EQUALS;
            case ">":
                return Operator.GREATER_THAN;
            case "<":
                return Operator.LESS_THAN;
            case ">=":
                return Operator.GREATER_THAN_OR_EQUAL;
            case "<=":
                return Operator.LESS_THAN_OR_EQUAL;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    @Override
    public boolean evaluate(Row row) {
        Object rowValue = row.getValue(column);

        switch (operator) {
            case EQUALS:
                return rowValue.equals(value);
            case NOT_EQUALS:
                return !rowValue.equals(value);
            case GREATER_THAN:
                return ((Comparable) rowValue).compareTo(value) > 0;
            case LESS_THAN:
                return ((Comparable) rowValue).compareTo(value) < 0;
            case GREATER_THAN_OR_EQUAL:
                return ((Comparable) rowValue).compareTo(value) >= 0;
            case LESS_THAN_OR_EQUAL:
                return ((Comparable) rowValue).compareTo(value) <= 0;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}