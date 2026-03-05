package DataOperations;

import java.util.List;

import Condition.AndCondition;
import Condition.Condition;
import Condition.OrCondition;
import Condition.SimpleCondition;
import Validation.ConditionValidator;
import Validation.TableMustExistValidator;
import Validation.Validator;
import models.Column;
import models.DataBase;
import models.DataType;
import models.Row;
import models.Schema;

public class QueryOperation extends AbstractOperation {
    private String query;
    private final List<Validator> validators = new java.util.ArrayList<>();

    public QueryOperation(DataBase database, String tableName, String query) {
        super(database, tableName);
        this.query = query;
        this.validators.add(new TableMustExistValidator(tableName, database));
        this.validators.add(new ConditionValidator(query, database, tableName));
    }

    @Override
    public List<Row> run() {
        validate();
        return execute();
    }

    @Override
    public List<Row> execute() {
        Schema schema = database.getTables().get(tableName).getSchema();
        String[] conditions = query.split("&&");
        List<Condition> orConditions1 = new java.util.ArrayList<>();

        for (String condition : conditions) {
            String[] orConditions = condition.split("\\|\\|");
            List<Condition> simpleConditions = new java.util.ArrayList<>();

            for (String orCondition : orConditions) {
                String[] parts = orCondition.split("(==|!=|>=|<=|>|<)");
                if (parts.length >= 2) {
                    String columnName = parts[0].trim();
                    String value = parts[1].trim();
                    String operator = orCondition.substring(columnName.length(), orCondition.length() - value.length()).trim();

                    Object convertValue = convertValue(schema, columnName, value);
                    simpleConditions.add(new SimpleCondition(columnName, operator, convertValue));
                }
            }
            orConditions1.add(new OrCondition(simpleConditions));
        }

        AndCondition andCondition = new AndCondition(orConditions1);

        List<Row> result = new java.util.ArrayList<>();
        for (Row row : database.getTables().get(tableName).getRows()) {
            if (andCondition.evaluate(row)) {
                result.add(row);
            }
        }
        return result;
    }
    
    private Object convertValue(Schema schema, String columnName, String value) {
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

    @Override
    public void validate() {
        for (Validator validatorn : validators) {
            validatorn.validator();
        }
    }
}