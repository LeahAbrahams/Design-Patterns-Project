package Validation;

import models.Column;
import models.DataBase;
import models.DataType;
import models.Schema;

public class ConditionValidator implements Validator {
    private String quary;
    private Schema schema;
    private DataBase dataBase;

    public ConditionValidator(String quary, DataBase dataBase, String tableName) {
        this.quary = quary;
        this.dataBase = dataBase;
        Schema schema = dataBase.getTables().get(tableName).getSchema();
    }

    public void validator() {
        String[] conditions = quary.split("&&|\\|\\|");

        for (String condition : conditions) {
            String[] parts = condition.split("(==|!=|>=|<=|>|<)");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid condition: " + condition);
            }

            String columnName = parts[0].trim();
            String value = parts[1].trim();
            DataType dataType = null;
            boolean b = false;
            for (Column column : schema.getColumns()) {
                if (column.getName().equals(columnName)) {
                    dataType = column.getDataType();
                    b = true;
                }
            }
            if (!b) {
                throw new IllegalArgumentException("No such column" + columnName);
            }
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
                throw new IllegalArgumentException("Value type does not match column " + columnName + " (expected " + dataType + ")");
            } catch (java.text.ParseException e) {
                throw new IllegalArgumentException("Value type does not match column " + columnName + " (expected date format yyyy-MM-dd)");
            }
        }
    }
}
