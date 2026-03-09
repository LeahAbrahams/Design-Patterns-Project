package Condition;

import models.Row;
import models.Schema;
import models.Column;
import models.DataType;

public class SimpleCondition implements Condition {
    private String column;
    private Operator operator;
    private Object valuee;

    public SimpleCondition(String column, String operatorStr, Object valuee) {
        this.column = column;
        this.operator = parseOperator(operatorStr);
        this.valuee = valuee;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return valuee;
    }

    @Override
    public void validate(Schema schema) {
        DataType dataType = null;
        for (Column col : schema.getColumns()) {
            if (col.getName().equals(column)) {
                dataType = col.getDataType();
                break;
            }
        }
        if (dataType == null) {
            throw new IllegalArgumentException("No such column: " + column);
        }
        
        String value = valuee.toString();
        try {
            switch (dataType) {
                case INTEGER:
                    Integer.parseInt(value);
                    break;
                case FLOAT:
                    Double.parseDouble(value);
                    break;
                case BOOLEAN:
                    if (!value.equals("true") && !value.equals("false")) {
                        throw new IllegalArgumentException("Value must be boolean");
                    }
                    break;
                case CHARACTER:
                    if (value.length() != 1) {
                        throw new IllegalArgumentException("Value must be a single character");
                    }
                    break;
                case DATE:
                    new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
                    break;
                case STRING:
                    break;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Value type does not match column " + column + " (expected " + dataType + ")");
        } catch (java.text.ParseException e) {
            throw new IllegalArgumentException("Value type does not match column " + column + " (expected date format yyyy-MM-dd)");
        }
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
                return rowValue.equals(valuee);
            case NOT_EQUALS:
                return !rowValue.equals(valuee);
            case GREATER_THAN:
                return ((Comparable) rowValue).compareTo(valuee) > 0;
            case LESS_THAN:
                return ((Comparable) rowValue).compareTo(valuee) < 0;
            case GREATER_THAN_OR_EQUAL:
                return ((Comparable) rowValue).compareTo(valuee) >= 0;
            case LESS_THAN_OR_EQUAL:
                return ((Comparable) rowValue).compareTo(valuee) <= 0;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
