package Validation;

import java.util.List;
import models.Column;
import models.DataBase;
import models.Row;
import models.Schema;
import models.Table;

public class SchemaMatchValidator implements Validator {
    String tableName;
    List<Row> rows;
    DataBase dataBase;

    public SchemaMatchValidator(String tableName, List<Row> rows, DataBase dataBase) {
        this.tableName = tableName;
        this.rows = rows;
        this.dataBase = dataBase;
    }

    @Override
    public void validator() {
        Table table = dataBase.getTable(tableName);
        Schema schema = table.getSchema();
        java.util.List<Column> columns = schema.getColumns();

        for (Row row : rows) {
            for (Column column : columns) {
                Object value = row.getValue(column.getName());

                if (value == null) {
                    throw new IllegalArgumentException("Missing value for column: " + column.getName());
                }

                switch (column.getDataType()) {
                    case STRING:
                        if (!(value instanceof String)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be STRING");
                        }
                        break;
                    case INTEGER:
                        if (!(value instanceof Integer)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be INTEGER");
                        }
                        break;
                    case FLOAT:
                        if (!(value instanceof Float) && !(value instanceof Double)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be FLOAT");
                        }
                        break;
                    case BOOLEAN:
                        if (!(value instanceof Boolean)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be BOOLEAN");
                        }
                        break;
                    case DATE:
                        if (!(value instanceof java.util.Date)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be DATE");
                        }
                        break;
                    case CHARACTER:
                        if (!(value instanceof Character)) {
                            throw new IllegalArgumentException("Column " + column.getName() + " must be CHARACTER");
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        for (Row row : rows) {
            for (String key : row.getValues().keySet()) {
                boolean exists = false;
                for (Column column : columns) {
                    if (column.getName().equals(key)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    throw new IllegalArgumentException("Column " + key + " does not exist in schema");
                }
            }
        }
    }
}