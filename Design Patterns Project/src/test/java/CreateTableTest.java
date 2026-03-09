import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Table;

public class CreateTableTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
    }

    @Test
    void testCreateTableWithColumns() {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING)
        );

        db.createTable("Users", columns);

        assertNotNull(db.getTable("Users"));
        assertEquals("Users", db.getTable("Users").getName());
        assertEquals(2, db.getTable("Users").getSchema().getColumns().size());
    }

    @Test
    void testCreateTableWithDifferentDataTypes() {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING),
                new Column("score", DataType.FLOAT),
                new Column("active", DataType.BOOLEAN),
                new Column("grade", DataType.CHARACTER)
        );

        db.createTable("Students", columns);

        Table table = db.getTable("Students");
        assertNotNull(table);
        assertEquals(5, table.getSchema().getColumns().size());
    }

    @Test
    void testCreateMultipleTables() {
        List<Column> columns1 = List.of(new Column("id", DataType.INTEGER));
        List<Column> columns2 = List.of(new Column("name", DataType.STRING));

        db.createTable("Table1", columns1);
        db.createTable("Table2", columns2);

        assertNotNull(db.getTable("Table1"));
        assertNotNull(db.getTable("Table2"));
        assertEquals(2, db.getTables().size());
    }

    @Test
    void testCreateEmptyTable() {
        List<Column> columns = List.of();

        db.createTable("EmptyTable", columns);

        assertNotNull(db.getTable("EmptyTable"));
        assertEquals(0, db.getTable("EmptyTable").getRows().size());
    }

    @Test
    void testTableSchemaCorrectness() {
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("email", DataType.STRING)
        );

        db.createTable("Contacts", columns);

        Table table = db.getTable("Contacts");
        assertEquals("id", table.getSchema().getColumns().get(0).getName());
        assertEquals(DataType.INTEGER, table.getSchema().getColumns().get(0).getDataType());
        assertEquals("email", table.getSchema().getColumns().get(1).getName());
        assertEquals(DataType.STRING, table.getSchema().getColumns().get(1).getDataType());
    }
}
