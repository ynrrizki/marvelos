package marvelos.illuminate.database.mysql;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Database {
    static final Dotenv dotenv = (Dotenv) Dotenv.configure()
            .directory("src/main/java/assets/")
            .filename(".env")
            .load();

    // JDBC Driver Name & Database URL
    private static final String  JDBC_DRIVER = dotenv.get("DB_DRIVER");

    private static final String JDBC_DB_URL = "jdbc:mysql://localhost:"+ dotenv.get("DB_PORT") + "/" + dotenv.get("DB_DATABASE");

    // JDBC Database Credentials
    private static final String JDBC_USER = dotenv.get("DB_USERNAME");
    private static final String JDBC_PASS = dotenv.get("DB_PASSWORD");

    // HikariCP
    private static ConnectionUtil _connUtil = new ConnectionUtil(JDBC_DRIVER, JDBC_DB_URL, JDBC_USER, JDBC_PASS);

    private Connection _conn;
    private static Database _instance = null;
    private PreparedStatement _statement;
    private ResultSet _resultSet;
    private int _resultUpdate;

    Database() {
        try {
            _conn = _connUtil.getDataSource().getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if(_instance == null) {
            return _instance = new Database();
        }
        return _instance;
    }

    public int queryUpdate(String query) {
        try {
            this.run(query);
            _resultUpdate = _statement.executeUpdate();
            return _resultUpdate;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return _resultUpdate;
    }

    public ResultSet query(String query) {
        try {
            this.run(query);
            _resultSet = _statement.executeQuery();
            return _resultSet;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return _resultSet = null;
    }

    public void run(String query) {
        try {
            _statement = _conn.prepareStatement(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConn() {
        try {
            _conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeStatement() {
        try {
            _statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResultSet() {
        try {
            _resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
