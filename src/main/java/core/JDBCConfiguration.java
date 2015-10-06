package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Valentyn on 10/6/2015.
 */
public class JDBCConfiguration {
    private static JDBCConfiguration configuration;
    private static Properties properties = Properties.getInstance();

    private JDBCConfiguration() {
    }

    public Connection getConnection() {
        try {
            Class.forName(properties.getJdbcDriverClass());
            return DriverManager.getConnection(properties.getJdbcUrl(), properties.getJdbcLogin(),
                    properties.getJdbcPass());
        } catch (SQLException | ClassNotFoundException ignore) {
            return null;
        }
    }

    public synchronized static JDBCConfiguration getInstance() {
        if (configuration == null) {
            return new JDBCConfiguration();
        }
        return configuration;
    }
}
