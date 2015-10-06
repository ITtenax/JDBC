package core;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Valentyn on 10/6/2015.
 */
public interface StationDAO {
    List<Station> getAllStationWithJDBC() throws SQLException;
    List<Station> getAllStationWithPool() throws SQLException;
}
