package japi;

import java.util.*;
import java.time.*;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TrainsDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Train> getAllTrains() {
        List<String> stations = Arrays.asList("Kalisz", "Ostrów Wielkopolski", "Poznań");
        return Arrays.asList(new Train("ciopong", stations),
                             new Train("ciufka", stations));
    }

    public List<TrainSchedule> getTrainSchedules(String trainName) {
        log.info("Getting train {}", trainName);
        List<TrainSchedule.Info> info = Arrays.asList(
                new TrainSchedule.Info("Kalisz", 1, LocalDateTime.of(2017, 10, 5, 15, 0), 3, LocalDateTime.of(2017, 10, 5, 15, 10)),
                new TrainSchedule.Info("Ostrów Wielkopolski", 1, LocalDateTime.of(2017, 10, 5, 16, 30), 3, LocalDateTime.of(2017, 10, 5, 16, 40)),
                new TrainSchedule.Info("Poznań", 100, LocalDateTime.of(2017, 10, 5, 17, 8), 3, LocalDateTime.of(2017, 10, 5, 17, 10)));

        return Arrays.asList(new TrainSchedule(50010, LocalDate.of(2017, 10, 5), info));
    }
}
