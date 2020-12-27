package japi;

import java.util.*;
import java.time.*;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class TrainsDAOTest {

    @Autowired
    private TrainsDAO trainsDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_getAllTrains() {
        Train train1 = new Train();
        train1.setName("train ipsum");
        train1.setStations(Arrays.asList("station 4", "station 3", "station 2"));

        Train train2 = new Train();
        train2.setName("train lorem");
        train2.setStations(Arrays.asList("station 1", "station 2", "station 3", "station 4"));

        assertEquals(Arrays.asList(train1, train2), trainsDAO.getAllTrains());
    }

    @Test
    public void test_getTrainSchedules() {
        TrainSchedule schedule1 = new TrainSchedule();
        schedule1.setId(100);
        schedule1.setDate(LocalDate.of(2017, 10, 10));

        TrainSchedule.Info info1 = new TrainSchedule.Info();
        info1.setStationName("station 1");
        info1.setArrivalDelay(1);
        info1.setArrivalTime(LocalDateTime.of(2017, 10, 10, 11, 0, 0));
        info1.setDepartureDelay(0);
        info1.setDepartureTime(LocalDateTime.of(2017, 10, 10, 11, 1, 0));

        TrainSchedule.Info info2 = new TrainSchedule.Info();
        info2.setStationName("station 2");
        info2.setArrivalDelay(1);
        info2.setArrivalTime(LocalDateTime.of(2017, 10, 10, 11, 10, 0));
        info2.setDepartureDelay(2);
        info2.setDepartureTime(LocalDateTime.of(2017, 10, 10, 11, 12, 0));

        TrainSchedule.Info info3 = new TrainSchedule.Info();
        info3.setStationName("station 3");
        info3.setArrivalDelay(10);
        info3.setArrivalTime(LocalDateTime.of(2017, 10, 10, 11, 14, 0));
        info3.setDepartureDelay(0);
        info3.setDepartureTime(LocalDateTime.of(2017, 10, 10, 11, 21, 0));

        TrainSchedule.Info info4 = new TrainSchedule.Info();
        info4.setStationName("station 4");
        info4.setArrivalDelay(1);
        info4.setArrivalTime(LocalDateTime.of(2017, 10, 10, 12, 10, 0));
        info4.setDepartureDelay(40);
        info4.setDepartureTime(LocalDateTime.of(2017, 10, 10, 12, 51, 0));

        TrainSchedule.Info info5 = new TrainSchedule.Info();
        info5.setStationName("station 5");
        info5.setArrivalDelay(-13);
        info5.setArrivalTime(LocalDateTime.of(2017, 10, 11, 1, 0, 0));
        info5.setDepartureDelay(0);
        info5.setDepartureTime(LocalDateTime.of(2017, 10, 11, 1, 5, 0));

        schedule1.setInfo(Arrays.asList(info1, info2, info3, info4, info5));

        TrainSchedule schedule2 = new TrainSchedule();
        schedule2.setId(101);
        schedule2.setDate(LocalDate.of(2017, 10, 11));

        TrainSchedule.Info info6 = new TrainSchedule.Info();
        info6.setStationName("station 1");
        info6.setArrivalDelay(1);
        info6.setArrivalTime(LocalDateTime.of(2017, 10, 11, 11, 0, 0));
        info6.setDepartureDelay(0);
        info6.setDepartureTime(LocalDateTime.of(2017, 10, 11, 11, 1, 0));

        TrainSchedule.Info info7 = new TrainSchedule.Info();
        info7.setStationName("station 2");
        info7.setArrivalDelay(1);
        info7.setArrivalTime(LocalDateTime.of(2017, 10, 11, 11, 10, 0));
        info7.setDepartureDelay(2);
        info7.setDepartureTime(LocalDateTime.of(2017, 10, 11, 11, 12, 0));

        TrainSchedule.Info info8 = new TrainSchedule.Info();
        info8.setStationName("station 3");
        info8.setArrivalDelay(10);
        info8.setArrivalTime(LocalDateTime.of(2017, 10, 11, 11, 14, 0));
        info8.setDepartureDelay(0);
        info8.setDepartureTime(LocalDateTime.of(2017, 10, 11, 11, 21, 0));

        TrainSchedule.Info info9 = new TrainSchedule.Info();
        info9.setStationName("station 4");
        info9.setArrivalDelay(1);
        info9.setArrivalTime(LocalDateTime.of(2017, 10, 11, 12, 10, 0));
        info9.setDepartureDelay(40);
        info9.setDepartureTime(LocalDateTime.of(2017, 10, 11, 12, 51, 0));

        schedule2.setInfo(Arrays.asList(info6, info7, info8, info9));

        assertEquals(Arrays.asList(schedule2, schedule1), trainsDAO.getTrainSchedules("train lorem"));
    }
}
