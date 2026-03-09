package Clone;

import models.Table;

public interface TableClone {
    Table clone() throws CloneNotSupportedException;
}
