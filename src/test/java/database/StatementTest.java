package database;

import app.models.User;
import org.junit.jupiter.api.Test;
//import vendor.marvelos.src.illuminate.database.mysql.Database;

import java.sql.*;

public class StatementTest {

    @Test
    public void queryStatementGetAll() throws SQLException {
        User user = new User();
        ResultSet rs = user.all();
        System.out.println("=======NEW COUT YANU=======");
        while(rs.next()) {
            System.out.println(rs.getString("name"));
            System.out.println(rs.getString("username"));
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
}
