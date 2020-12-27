package japi;

import java.util.*;
import java.time.*;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class TrainsDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String getAllTrainsQuery =
        "SELECT train_name, GROUP_CONCAT(station_name ORDER BY stop_number) AS stations " +
        "FROM schedule_info " +
        "JOIN (SELECT train_name, MAX(schedule_id) AS last_schedule_id " +
        "      FROM schedule JOIN train ON schedule.train_id = train.train_id GROUP BY train.train_id) t " +
        "    ON t.last_schedule_id = schedule_info.schedule_id " +
        "JOIN station ON station.station_id = schedule_info.station_id " +
        "GROUP BY train_name ORDER BY train_name";

    private static final String getTrainIdQuery =
        "SELECT train_id FROM train WHERE train_name = ?";

    private static final String getTrainSchedulesQuery =
        "SELECT schedule_id, schedule_date FROM schedule INNER JOIN train ON train.train_id = schedule.train_id " +
        "WHERE schedule.train_id = ? ORDER BY schedule_date DESC";

    private static final String getTrainScheduleInfosQuery =
        "SELECT station_name, departure_time, departure_delay, arrival_time, arrival_delay " +
        "FROM schedule_info INNER JOIN station ON schedule_info.station_id = station.station_id " +
        "WHERE schedule_id = ? ORDER BY stop_number";

    public List<Train> getAllTrains() {
        return jdbcTemplate.query(getAllTrainsQuery, (rs, rowNum) -> {
            Train train = new Train();
            train.setName(rs.getString(1));
            train.setStations(Arrays.asList(rs.getString(2).split(",")));
            return train;
        });
    }

    public List<TrainSchedule> getTrainSchedules(String trainName) {
        int trainId = getTrainId(trainName);

        List<TrainSchedule> schedules = jdbcTemplate.query(getTrainSchedulesQuery, (stmt) -> stmt.setInt(1, trainId),
                (rs, rowNum) -> {
                    TrainSchedule schedule = new TrainSchedule();
                    schedule.setId(rs.getInt(1));
                    schedule.setDate(rs.getDate(2).toLocalDate());
                    return schedule;
                });

        for (TrainSchedule schedule: schedules) {
            schedule.setInfo(jdbcTemplate.query(getTrainScheduleInfosQuery, (stmt) -> stmt.setInt(1, schedule.getId()),
                    (rs, rowNum) -> {
                        TrainSchedule.Info info = new TrainSchedule.Info();
                        info.setStationName(rs.getString(1));
                        info.setDepartureTime(rs.getTimestamp(2).toLocalDateTime());
                        info.setDepartureDelay(rs.getInt(3));
                        info.setArrivalTime(rs.getTimestamp(4).toLocalDateTime());
                        info.setArrivalDelay(rs.getInt(5));
                        return info;
                    }));
        }

        return schedules;
    }

    private int getTrainId(String trainName) {
        List<Integer> ids = jdbcTemplate.query(getTrainIdQuery, (stmt) -> stmt.setString(1, trainName),
                (rs, rowNum) -> rs.getInt(1));
        return ids.get(0);
    }
}
