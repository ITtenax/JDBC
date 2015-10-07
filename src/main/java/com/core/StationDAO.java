package com.core;

import java.sql.SQLException;
import java.util.List;

import com.bean.Station;

/**
 * Created by Valentyn on 10/6/2015.
 */
public interface StationDAO {
    List<Station> getAllStationWithH2Server() throws SQLException;
    List<Station> getAllStationWithPool() throws SQLException;
    List<Station> getAllStationWithH2Embedded() throws SQLException;
}
