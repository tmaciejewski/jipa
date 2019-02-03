package japi;

import java.util.List;
import java.time.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TrainSchedule {
    @JsonProperty("schedule_id")
    int id;

    @JsonProperty("schedule_date")
    LocalDate date;
    List<Info> info;

    @Value
    public static class Info {
        @JsonProperty("station_name")
        String stationName;

        @JsonProperty("arrival_delay")
        int arrivalDelay;

        @JsonProperty("arrival_time")
        LocalDateTime arrivalTime;

        @JsonProperty("departure_delay")
        int departureDelay;

        @JsonProperty("departure_time")
        LocalDateTime departureTime;
    }
}
