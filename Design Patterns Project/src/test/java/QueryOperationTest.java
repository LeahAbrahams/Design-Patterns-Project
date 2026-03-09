import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import models.DataBase;
import models.Column;
import models.DataType;
import models.Row;
import Condition.SimpleCondition;
import Condition.AndCondition;
import Condition.OrCondition;

public class QueryOperationTest {
    private DataBase db;

    @BeforeEach
    void setUp() {
        db = new DataBase("TestDB");
        List<Column> columns = List.of(
                new Column("id", DataType.INTEGER),
                new Column("name", DataType.STRING),
                new Column("age", DataType.INTEGER),
                new Column("score", DataType.FLOAT)
        );
        db.createTable("Students", columns);

        Row row1 = new Row();
        row1.setValue("id", 1);
        row1.setValue("name", "Sara");
        row1.setValue("age", 20);
        row1.setValue("score", 85.5);

        Row row2 = new Row();
        row2.setValue("id", 2);
        row2.setValue("name", "John");
        row2.setValue("age", 22);
        row2.setValue("score", 90.0);

        Row row3 = new Row();
        row3.setValue("id", 3);
        row3.setValue("name", "Alice");
        row3.setValue("age", 21);
        row3.setValue("score", 95.5);

        db.insertIntoTable("Students", List.of(row1, row2, row3));
    }

    @Test
    void testQueryWithEqualsCondition() {
        SimpleCondition condition = new SimpleCondition("name", "==", "Sara");
        List<Row> result = db.query(condition, "Students");

        assertEquals(1, result.size());
        assertEquals("Sara", result.get(0).getValue("name"));
    }

    @Test
    void testQueryWithGreaterThanCondition() {
        SimpleCondition condition = new SimpleCondition("age", ">", 20);
        List<Row> result = db.query(condition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryWithLessThanCondition() {
        SimpleCondition condition = new SimpleCondition("score", "<", 90.0);
        List<Row> result = db.query(condition, "Students");

        assertEquals(1, result.size());
        assertEquals("Sara", result.get(0).getValue("name"));
    }

    @Test
    void testQueryWithGreaterThanOrEqualCondition() {
        SimpleCondition condition = new SimpleCondition("score", ">=", 90.0);
        List<Row> result = db.query(condition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryWithLessThanOrEqualCondition() {
        SimpleCondition condition = new SimpleCondition("age", "<=", 21);
        List<Row> result = db.query(condition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryWithNotEqualsCondition() {
        SimpleCondition condition = new SimpleCondition("name", "!=", "Sara");
        List<Row> result = db.query(condition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryWithAndCondition() {
        SimpleCondition condition1 = new SimpleCondition("age", ">", 20);
        SimpleCondition condition2 = new SimpleCondition("score", ">=", 90.0);
        AndCondition andCondition = new AndCondition(List.of(condition1, condition2));

        List<Row> result = db.query(andCondition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryWithOrCondition() {
        SimpleCondition condition1 = new SimpleCondition("name", "==", "Sara");
        SimpleCondition condition2 = new SimpleCondition("name", "==", "Alice");
        OrCondition orCondition = new OrCondition(List.of(condition1, condition2));

        List<Row> result = db.query(orCondition, "Students");

        assertEquals(2, result.size());
    }

    @Test
    void testQueryNoResults() {
        SimpleCondition condition = new SimpleCondition("age", ">", 100);
        List<Row> result = db.query(condition, "Students");

        assertEquals(0, result.size());
    }

    @Test
    void testQueryAllResults() {
        SimpleCondition condition = new SimpleCondition("age", ">", 0);
        List<Row> result = db.query(condition, "Students");

        assertEquals(3, result.size());
    }

    @Test
    void testQueryOnNonExistentTable() {
        SimpleCondition condition = new SimpleCondition("id", "==", 1);

        assertThrows(NullPointerException.class, () -> {
            db.query(condition, "NonExistent");
        });
    }
}
