import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Row;

public class UpdateOperationTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING),
                new Column("score", DataType.FLOAT)
        );
        db.createTable("Students", columns);
    }

    @Test
    void testUpdateSingleRow() {
        Row oldRow = new Row();
        oldRow.setValue("id", 1);
        oldRow.setValue("name", "Sara");
        oldRow.setValue("score", 85.0);

        db.insertIntoTable("Students", List.of(oldRow));

        Row newRow = new Row();
        newRow.setValue("id", 1);
        newRow.setValue("name", "Sara Updated");
        newRow.setValue("score", 95.0);

        List<Row> updated = db.updateTable("Students", List.of(oldRow), List.of(newRow));

        assertEquals(1, updated.size());
        assertEquals("Sara Updated", db.getTable("Students").getRows().get(0).getValue("name"));
        assertEquals(95.0, db.getTable("Students").getRows().get(0).getValue("score"));
    }

    @Test
    void testUpdateMultipleRows() {
        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");
        row1.setValue("score", 85.0);

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");
        row2.setValue("score", 75.0);

        db.insertIntoTable("Students", List.of(row1, row2));

        Row newRow1 = new Row();
        newRow1.setValue("id", 1);
        newRow1.setValue("name", "Sara Updated");
        newRow1.setValue("score", 95.0);

        Row newRow2 = new Row();
        newRow2.setValue("id", 2);
        newRow2.setValue("name", "John Updated");
        newRow2.setValue("score", 85.0);

        List<Row> updated = db.updateTable("Students", List.of(row1, row2), List.of(newRow1, newRow2));

        assertEquals(2, updated.size());
        assertEquals("Sara Updated", db.getTable("Students").getRows().get(0).getValue("name"));
        assertEquals("John Updated", db.getTable("Students").getRows().get(1).getValue("name"));
    }

    @Test
    void testUpdatePartialFields() {
        Row oldRow = new Row();
        oldRow.setValue("id", 1);
        oldRow.setValue("name", "Sara");
        oldRow.setValue("score", 85.0);

        db.insertIntoTable("Students", List.of(oldRow));

        Row newRow = new Row();
        newRow.setValue("id", 1);
        newRow.setValue("name", "Sara");
        newRow.setValue("score", 90.0);

        List<Row> updated = db.updateTable("Students", List.of(oldRow), List.of(newRow));

        assertEquals(1, updated.size());
        assertEquals("Sara", db.getTable("Students").getRows().get(0).getValue("name"));
        assertEquals(90.0, db.getTable("Students").getRows().get(0).getValue("score"));
    }

    @Test
    void testUpdateNonExistentRow() {
        Row oldRow = new Row();
        oldRow.setValue("id", 999);
        oldRow.setValue("name", "Ghost");
        oldRow.setValue("score", 0.0);

        Row newRow = new Row();
        newRow.setValue("id", 999);
        newRow.setValue("name", "Updated Ghost");
        newRow.setValue("score", 100.0);

        assertThrows(RuntimeException.class, () -> {
            db.updateTable("Students", List.of(oldRow), List.of(newRow));
        });
    }

    @Test
    void testUpdateInNonExistentTable() {
        Row oldRow = new Row();
        oldRow.setValue("id", 1);

        Row newRow = new Row();
        newRow.setValue("id", 1);

        assertThrows(IllegalArgumentException.class, () -> {
            db.updateTable("NonExistent", List.of(oldRow), List.of(newRow));
        });
    }
}
