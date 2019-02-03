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
        List<Train> trains = Arrays.asList(new Train("ciopong", stations1),
                                           new Train("ciufka", stations2));

        Mockito.when(trainsDAO.getAllTrains()).thenReturn(trains);

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
        List<TrainSchedule.Info> info = Arrays.asList(
                new TrainSchedule.Info("Kalisz", 1, LocalDateTime.of(2017, 10, 5, 15, 0), 3, LocalDateTime.of(2017, 10, 5, 15, 10)),
                new TrainSchedule.Info("Ostrów Wielkopolski", 4, LocalDateTime.of(2017, 10, 5, 16, 30), 13, LocalDateTime.of(2017, 10, 5, 16, 40)),
                new TrainSchedule.Info("Poznań", 100, LocalDateTime.of(2017, 10, 5, 17, 8), 3, LocalDateTime.of(2017, 10, 5, 17, 10)));
        List<TrainSchedule> schedules = Arrays.asList(new TrainSchedule(123, LocalDate.of(2017, 1, 1), info));

        Mockito.when(trainsDAO.getTrainSchedules("train name")).thenReturn(schedules);

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
        List<TrainSchedule> schedules = Arrays.asList(new TrainSchedule(123, LocalDate.of(2017, 1, 1), Collections.emptyList()));

        Mockito.when(trainsDAO.getTrainSchedules("train/name")).thenReturn(schedules);

        mvc.perform(get("/api/trains/train/name")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("schedules", hasSize(1)))
            .andExpect(jsonPath("schedules[0].schedule_id", is(123)))
            .andExpect(jsonPath("schedules[0].schedule_date", is("2017-01-01")));
    }
}
