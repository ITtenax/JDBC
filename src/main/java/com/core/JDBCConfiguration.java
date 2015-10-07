package com.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.helpers.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Created by Valentyn on 10/6/2015.
 */
public class JDBCConfiguration {
    private static JDBCConfiguration configuration;
    private static Properties properties = Properties.getInstance();

    private static BasicDataSource connectionPool;

    static {
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(properties.getJdbcLogin());
        connectionPool.setPassword(properties.getJdbcPass());
        connectionPool.setDriverClassName(properties.getJdbcDriverClass());
        connectionPool.setUrl(properties.getJdbcUrl());
        connectionPool.setInitialSize(properties.getPoolSize());
    }

    private JDBCConfiguration() {
    }

    public Connection getEmbeddedConnection() throws SQLException{
        connectionPool.setUrl(properties.getJdbcUrlEmbedded());
       return connectionPool.getConnection();
    }

    public Connection getServerConnection() {
        return getConnection(properties.getJdbcUrl());
    }

    public Connection getPoolConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    private Connection getConnection(String dbUrl) {
        try {
            Class.forName(properties.getJdbcDriverClass());
            return DriverManager.getConnection(dbUrl, properties.getJdbcLogin(),
                    properties.getJdbcPass());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
