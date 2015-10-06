package core;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.BeanProcessor;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static core.JDBCConfiguration.*;
import static core.SQLConstants.SELECT_ALL;

/**
 * Created by Valentyn on 10/6/2015.
 */
public class StationActionDao implements StationDAO {
    private BasicDataSource connectionPool;
    private static Properties properties = Properties.getInstance();

    public StationActionDao() throws URISyntaxException, SQLException {
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(properties.getJdbcLogin());
        connectionPool.setPassword(properties.getJdbcPass());
        connectionPool.setDriverClassName(properties.getJdbcDriverClass());
        connectionPool.setUrl(properties.getJdbcUrl());
        connectionPool.setInitialSize(properties.getPoolSize());
    }

    @Override
    public List<Station> getAllStationWithJDBC() {
        List<Station> stations = new ArrayList<>();
        try (Connection connection = getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                stations.add((Station) new BeanProcessor().toBean(resultSet, Station.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    @Override
    public List<Station> getAllStationWithPool() {
        List<Station> stations = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                stations.add((Station) new BeanProcessor().toBean(resultSet, Station.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
