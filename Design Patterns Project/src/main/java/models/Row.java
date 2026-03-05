package models;

import java.util.HashMap;
import java.util.Map;

public class Row implements Cloneable {
    private Map<String, Object> values = new java.util.HashMap<>();

    public Row(Map<String, Object> values) {
        this.values = values;
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

    @Override
    public Row clone() {
        // ההעתקה הראשונית היא העתקה רדודה אך מכיוון שמדובר בהעתקה של טיפוסים פשוטים אין בזה בעיה
        Map<String, Object> clonedValues = new HashMap<>(this.values);
        return new Row(clonedValues);
    }
}
