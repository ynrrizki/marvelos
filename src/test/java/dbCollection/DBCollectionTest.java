package dbCollection;

import marvelos.src.illuminate.DBCollection;
import org.junit.jupiter.api.Test;

public class DBCollectionTest {
    @Test
    public void getColumn() {
        String[] column = {
                "column1", "column2",
        };

        String[] value = {
                "value1", "value2",
        };
        DBCollection db = new DBCollection(column, value);
    }
}
