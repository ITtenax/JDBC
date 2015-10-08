package unit;


import com.bean.Station;
import com.core.StationDAO;
import com.core.StationActionDao;
import com.helpers.Properties;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Valentyn on 10/5/2015.
 */
public class Tests {
    List<Station> stationList = Arrays.asList(new Station("13", "Phoenix", "AZ", "33.0", "112.0"),
            new Station("44", "Denver", "CO", "40.0", "105.0"),
            new Station("66", "Caribou", "ME", "47.0", "68.0"));

    @Test
    public void unitTestPart2() throws SQLException, URISyntaxException {
        StationDAO stationDAO = new StationActionDao();
        for (int i = 0; i < 1000; i++) {
            List<Station> stationsDB = stationDAO.getAllStationWithH2Server();
            stationsDB.stream().forEach(out::println);
            assertThat(stationList, equalTo(stationsDB));
        }
    }

    @Test
    public void unitTestPart3() throws SQLException, URISyntaxException {
        StationDAO stationDAO = new StationActionDao();
        for (int i = 0; i < 1000; i++) {
            List<Station> stationsDB = stationDAO.getAllStationWithPool();
            stationsDB.stream().forEach(out::println);
            assertThat(stationList, equalTo(stationsDB));
        }
    }

    @Test
    public void unitTestPart4() throws SQLException, URISyntaxException {
        StationDAO stationDAO = new StationActionDao();
        for (int i = 0; i < 10000; i++) {
            List<Station> stationsDB = stationDAO.getAllStationWithH2Embedded();
            stationsDB.stream().forEach(out::println);
            assertThat(stationList, equalTo(stationsDB));
        }
    }

    @Test
    public void unitTestPart5() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        long start = 0;
        for (int i = 0; i < 2000_00; i++) {
            executorService.execute(() -> {
                List<Station> stations = null;
                try {
                    stations = new StationActionDao().getAllStationWithH2Embedded();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stations.stream().forEach(out::println);
            });
            start = System.currentTimeMillis();
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
        out.println("Done after " + (System.currentTimeMillis() - start));
    }
}

