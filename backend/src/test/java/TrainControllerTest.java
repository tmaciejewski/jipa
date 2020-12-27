package japi;

import java.util.*;
import java.time.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainController.class)
public class TrainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrainsDAO trainsDAO;

    @Autowired
    private TrainController trainController;

    @Test
    public void test_getAllTrains() throws Exception {
        List<String> stations1 = Arrays.asList("Kalisz", "Ostrów Wielkopolski", "Poznań");
        List<String> stations2 = Arrays.asList("Gdynia", "Sopot");

        Train train1 = new Train();
        train1.setName("ciopong");
        train1.setStations(stations1);

        Train train2 = new Train();
        train2.setName("ciufka");
        train2.setStations(stations2);

        Mockito.when(trainsDAO.getAllTrains()).thenReturn(Arrays.asList(train1, train2));

        mvc.perform(get("/api/trains")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("trains", hasSize(2)))
            .andExpect(jsonPath("trains[0].train_name", is("ciopong")))
            .andExpect(jsonPath("trains[0].stations", is(stations1)))
            .andExpect(jsonPath("trains[1].train_name", is("ciufka")))
            .andExpect(jsonPath("trains[1].stations", is(stations2)));
    }

    @Test
    public void test_getTrainSchedules() throws Exception {
        TrainSchedule.Info info1 = new TrainSchedule.Info();
        info1.setStationName("Kalisz");
        info1.setArrivalDelay(1);
        info1.setArrivalTime(LocalDateTime.of(2017, 10, 5, 15, 0));
        info1.setDepartureDelay(3);
        info1.setDepartureTime(LocalDateTime.of(2017, 10, 5, 15, 10));

        TrainSchedule.Info info2 = new TrainSchedule.Info();
        info2.setStationName("Ostrów Wielkopolski");
        info2.setArrivalDelay(4);
        info2.setArrivalTime(LocalDateTime.of(2017, 10, 5, 16, 30));
        info2.setDepartureDelay(13);
        info2.setDepartureTime(LocalDateTime.of(2017, 10, 5, 16, 40));

        TrainSchedule.Info info3 = new TrainSchedule.Info();
        info3.setStationName("Poznań");
        info3.setArrivalDelay(100);
        info3.setArrivalTime(LocalDateTime.of(2017, 10, 5, 17, 8));
        info3.setDepartureDelay(3);
        info3.setDepartureTime(LocalDateTime.of(2017, 10, 5, 17, 10));

        TrainSchedule schedule = new TrainSchedule();
        schedule.setId(123);
        schedule.setDate(LocalDate.of(2017, 1, 1));
        schedule.setInfo(Arrays.asList(info1, info2, info3));

        Mockito.when(trainsDAO.getTrainSchedules("train name")).thenReturn(Arrays.asList(schedule));

        mvc.perform(get("/api/trains/train name")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("schedules", hasSize(1)))
            .andExpect(jsonPath("schedules[0].schedule_id", is(123)))
            .andExpect(jsonPath("schedules[0].schedule_date", is("2017-01-01")))
            .andExpect(jsonPath("schedules[0].info", hasSize(3)))
            .andExpect(jsonPath("schedules[0].info[0].station_name", is("Kalisz")))
            .andExpect(jsonPath("schedules[0].info[0].arrival_delay", is(1)))
            .andExpect(jsonPath("schedules[0].info[0].arrival_time", is("2017-10-05T15:00:00")))
            .andExpect(jsonPath("schedules[0].info[0].departure_delay", is(3)))
            .andExpect(jsonPath("schedules[0].info[0].departure_time", is("2017-10-05T15:10:00")))
            .andExpect(jsonPath("schedules[0].info[1].station_name", is("Ostrów Wielkopolski")))
            .andExpect(jsonPath("schedules[0].info[1].arrival_delay", is(4)))
            .andExpect(jsonPath("schedules[0].info[1].arrival_time", is("2017-10-05T16:30:00")))
            .andExpect(jsonPath("schedules[0].info[1].departure_delay", is(13)))
            .andExpect(jsonPath("schedules[0].info[1].departure_time", is("2017-10-05T16:40:00")))
            .andExpect(jsonPath("schedules[0].info[2].station_name", is("Poznań")))
            .andExpect(jsonPath("schedules[0].info[2].arrival_delay", is(100)))
            .andExpect(jsonPath("schedules[0].info[2].arrival_time", is("2017-10-05T17:08:00")))
            .andExpect(jsonPath("schedules[0].info[2].departure_delay", is(3)))
            .andExpect(jsonPath("schedules[0].info[2].departure_time", is("2017-10-05T17:10:00")));
    }

    @Test
    public void test_getTrainNameWithSlash() throws Exception {
        TrainSchedule schedule = new TrainSchedule();
        schedule.setId(123);
        schedule.setDate(LocalDate.of(2017, 1, 1));
        schedule.setInfo(Collections.emptyList());

        Mockito.when(trainsDAO.getTrainSchedules("train/name")).thenReturn(Arrays.asList(schedule));

        mvc.perform(get("/api/trains/train/name")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("schedules", hasSize(1)))
            .andExpect(jsonPath("schedules[0].schedule_id", is(123)))
            .andExpect(jsonPath("schedules[0].schedule_date", is("2017-01-01")));
    }
}
