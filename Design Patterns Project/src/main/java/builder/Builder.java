package builder;

import models.DataType;
import models.Table;

public interface Builder {
    void setName(String name);
    void addColumn(String columnName, DataType dataType);
    Table getTable();
    void build(String name, java.util.List<models.Column> columns);
}