import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Row;

public class InsertOperationTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
    }

    @Test
    void testInsertSingleRow() {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );
        db.createTable("Users", columns);

        Row row = new Row();
        row.setValue("id", 1);
        row.setValue("name", "Sara");

        List<Row> inserted = db.insertIntoTable("Users", List.of(row));

        assertEquals(1, inserted.size());
        assertEquals(1, db.getTable("Users").getRows().size());
    }

    @Test
    void testInsertMultipleRows() {
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

        List<Row> inserted = db.insertIntoTable("Users", List.of(row1, row2));

        assertEquals(2, inserted.size());
    }

    @Test
    void testInsertIntoNonExistentTable() {
        Row row = new Row();
        row.setValue("id", 1);

        assertThrows(IllegalArgumentException.class, () -> {
            db.insertIntoTable("NonExistent", List.of(row));
        });
    }
}
