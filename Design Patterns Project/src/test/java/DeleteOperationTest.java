import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Row;

public class DeleteOperationTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );
        db.createTable("Users", columns);
    }

    @Test
    void testDeleteSingleRow() {
        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");

        db.insertIntoTable("Users", List.of(row1, row2));

        List<Row> deleted = db.deleteFromTable("Users", List.of(row1));

        assertEquals(1, deleted.size());
        assertEquals(1, db.getTable("Users").getRows().size());
        assertEquals("John", db.getTable("Users").getRows().get(0).getValue("name"));
    }

    @Test
    void testDeleteMultipleRows() {
        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");

        Row row3 = new Row();
        row3.setValue("id", 3);
        row3.setValue("name", "Alice");

        db.insertIntoTable("Users", List.of(row1, row2, row3));

        List<Row> deleted = db.deleteFromTable("Users", List.of(row1, row3));

        assertEquals(2, deleted.size());
        assertEquals(1, db.getTable("Users").getRows().size());
        assertEquals("John", db.getTable("Users").getRows().get(0).getValue("name"));
    }

    @Test
    void testDeleteAllRows() {
        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");

        db.insertIntoTable("Users", List.of(row1, row2));

        List<Row> deleted = db.deleteFromTable("Users", List.of(row1, row2));

        assertEquals(2, deleted.size());
        assertEquals(0, db.getTable("Users").getRows().size());
    }

    @Test
    void testDeleteNonExistentRow() {
        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");

        db.insertIntoTable("Users", List.of(row1));

        Row nonExistent = new Row();
        nonExistent.setValue("id", 999);
        nonExistent.setValue("name", "Ghost");

        assertThrows(RuntimeException.class, () -> {
            db.deleteFromTable("Users", List.of(nonExistent));
        });
    }

    @Test
    void testDeleteFromNonExistentTable() {
        Row row = new Row();
        row.setValue("id", 1);

        assertThrows(IllegalArgumentException.class, () -> {
            db.deleteFromTable("NonExistent", List.of(row));
        });
    }
}
