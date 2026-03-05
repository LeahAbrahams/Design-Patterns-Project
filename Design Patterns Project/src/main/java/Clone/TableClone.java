package Clone;

import models.Table;

public class TableClone {
    public static Table cloneTable(Table original) {
        // אם הטבלה המקורית לא קיימת
        if (original == null) {
            return null;
        }
        return original.clone();
    }
}