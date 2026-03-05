package Iteration;

import java.util.ArrayList;
import java.util.List;

import Condition.AndCondition;
import Condition.Condition;
import Condition.OrCondition;
import Condition.SimpleCondition;
import models.Column;
import models.DataType;
import models.Row;
import models.Schema;

public class ByConditionOperation implements Iiteration {
    private String query;
    private Schema schema;

    public ByConditionOperation(String query, Schema schema) {
        this.query = query;
        this.schema = schema;
    }

    @Override
    public List<Row> iterate(List<Row> rows) {
        AndCondition Condition = buildCondition();
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if (Condition.evaluate(row)) {
                result.add(row);
            }
        }
        return result;
    }

    private AndCondition buildCondition() {
        String[] conditions = query.split("&&");
        List<Condition> orConditions1 = new ArrayList<>();

        for (String condition : conditions) {
            String[] orConditions = condition.split("\\|\\|");
            List<Condition> simpleConditions = new ArrayList<>();

            for (String orCondition : orConditions) {
                String[] parts = orCondition.split("(==|!=|>=|<=|>|<)");
                if (parts.length >= 2) {
                    String columnName = parts[0].trim();
                    String value = parts[1].trim();
                    String operator = orCondition.substring(columnName.length(), orCondition.length() - value.length()).trim();

                    Object convertValue = convertValue(columnName, value);
                    simpleConditions.add(new SimpleCondition(columnName, operator, convertValue));
                }
            }
            orConditions1.add(new OrCondition(simpleConditions));
        }
        return new AndCondition(orConditions1);
    }

    private Object convertValue(String columnName, String value) {
        for (Column column : schema.getColumns()) {
            if (column.getName().equals(columnName)) {
                DataType dataType = column.getDataType();
                switch (dataType) {
                    case INTEGER:
                        return Integer.parseInt(value);
                    case FLOAT:
                        return Double.parseDouble(value);
                    case BOOLEAN:
                        return Boolean.parseBoolean(value);
                    case CHARACTER:
                        return value.charAt(0);
                    case DATE:
                        try {
                            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
                        } catch (java.text.ParseException e) {
                            return value;
                        }
                    case STRING:
                        return value;
                }
            }
        }
        return value;
    }
}
