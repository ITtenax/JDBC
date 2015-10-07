package com.core;

import org.apache.commons.dbutils.BeanProcessor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Station;


import static com.core.JDBCConfiguration.*;
import static com.helpers.SQLConstants.SELECT_ALL;

/**
 * Created by Valentyn on 10/6/2015.
 */
public class StationActionDao implements StationDAO {

    @Override
    public List<Station> getAllStationWithH2Server() throws SQLException {
        return selectAllStation(getInstance().getServerConnection());
    }

    @Override
    public List<Station> getAllStationWithPool() throws SQLException {
        return selectAllStation(getInstance().getPoolConnection());
    }

    @Override
    public List<Station> getAllStationWithH2Embedded() throws SQLException {
        return selectAllStation(getInstance().getEmbeddedConnection());
    }


    private List<Station> selectAllStation(Connection conn){
        List<Station> stations = new ArrayList<>();
        try (Connection connection = conn;
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
