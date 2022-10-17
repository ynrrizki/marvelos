package marvelos.illuminate.database.mysql;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
    private static HikariDataSource dataSource;

    ConnectionUtil(String driverM, String db_url, String user, String pass) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverM);
        config.setJdbcUrl(db_url);
        config.setUsername(user);
        config.setPassword(pass);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
