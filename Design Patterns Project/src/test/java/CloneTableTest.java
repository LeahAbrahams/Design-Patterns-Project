import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Table;
import models.Row;

public class CloneTableTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
    }

    @Test
    void testCloneEmptyTable() throws CloneNotSupportedException {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );
        db.createTable("Users", columns);

        Table original = db.getTable("Users");
        Table cloned = original.clone();

        assertNotNull(cloned);
        assertEquals(original.getName(), cloned.getName());
        assertNotSame(original, cloned);
    }

    @Test
    void testCloneTableWithData() throws CloneNotSupportedException {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );
        db.createTable("Users", columns);

        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");

        db.insertIntoTable("Users", List.of(row1, row2));

        Table original = db.getTable("Users");
        Table cloned = original.clone();

        assertEquals(2, cloned.getRows().size());
        assertNotSame(original.getRows(), cloned.getRows());
    }

    @Test
    void testClonedTableIsIndependent() throws CloneNotSupportedException {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );
        db.createTable("Users", columns);

        Row row = new Row();
        row.setValue("id", 1);
        row.setValue("name", "Sara");

        db.insertIntoTable("Users", List.of(row));

        Table original = db.getTable("Users");
        Table cloned = original.clone();

        Row newRow = new Row();
        newRow.setValue("id", 2);
        newRow.setValue("name", "John");
        cloned.addRow(newRow);

        assertEquals(1, original.getRows().size());
        assertEquals(2, cloned.getRows().size());
    }
}
