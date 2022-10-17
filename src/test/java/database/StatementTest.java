package database;

import app.models.User;
import marvelos.illuminate.DBCollection;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class StatementTest {

    @Test
    public void queryStatementGetAll() throws SQLException {
        User user = new User();
        ResultSet rs = user.all();
        System.out.println("=======NEW COUT YANU=======");
        while(rs.next()) {
            System.out.println(rs.getString("email"));
//            System.out.println(rs.getString("username"));
        }
        System.out.println("=======NEW COUT YANU=======");
    }

    @Test
    public void queryStatementGetCount() throws SQLException {
        User user = new User();
        System.out.println("=======NEW COUT YANU=======");
         System.out.println(user.count());
        System.out.println(user.where("level", "mapper").count());
        System.out.println("=======NEW COUT YANU=======");
    }

    @Test
    public void queryGetTableName() throws SQLException {
        User user = new User();
        System.out.println("=======NEW COUT YANU=======");
        System.out.println(user.getTable());
        System.out.println("=======NEW COUT YANU=======");
    }

    @Test
    public void queryGetWhere() throws SQLException {
        User user = new User();
        System.out.println("=======NEW COUT YANU=======");
        ResultSet rs = user.where("level", "ADMIN").get();
        while (rs.next()) {
            System.out.println(rs.getString("nama_lengkap"));
        }
        System.out.println("=======NEW COUT YANU=======");
    }
    @Test
    public void queryInsert() {
        User user = new User();
        String[] column = {
               "name", "email", "password", "level"
        };
        String[] value = {
                "Yanto", "yanto@gmail.com", "$2y$10$dKsQI1TUG2y8M6NeRWA4F.o460fgbP1JhXHZhupEvZeTXLnoQTIRy", "ADMIN"
        };

        DBCollection query = new DBCollection(column, value);
        // String[][] query = {
        //        {"name", "email", "password", "level"},
        //        {"TestData", "testData@gmail.com", "password", "WAITER"}
        // };
        System.out.println("=======NEW COUT YANU=======");
        user.save(query);
        System.out.println("=======NEW COUT YANU=======");
    }

    @Test
    public void queryUpdate() {
        User user = new User();
        var data = user.find(7);
        String[] column = {"name", "email", "level"};
        String[] value = {"Yani", "yanto@gmail.com", "ADMIN"};
        DBCollection query = new DBCollection(column, value);
        System.out.println("=======NEW COUT YANU=======");
        data.update(query);
        System.out.println("=======NEW COUT YANU=======");
    }

    @Test
    public void queryDelete() {
        User user = new User();
        var data = user.find(7);
        System.out.println("=======NEW COUT YANU=======");
        data.delete();
        System.out.println("=======NEW COUT YANU=======");
    }
}
