package models;

import java.util.Map;

public class Row {
    private Map<String, Object> values = new java.util.HashMap<>();

    public Row() {
    }

    public Row(Map<String, Object> values) {
        this.values = new java.util.HashMap<>(values);
    }

    public Object getValue(String objectName) {
        return values.get(objectName);
    }

    public void setValue(String objectName, Object value) {
        values.put(objectName, value);
    }

    public Map<String, Object> getValues() {
        return values;
    }
}
