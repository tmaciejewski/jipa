package japi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.net.URLDecoder;

@RestController
public class TrainController {

    @Autowired
    private TrainsDAO trainsDAO;

    @RequestMapping("/api/trains")
    public Map<String, List<Train>> allTrains() {
        return Collections.singletonMap("trains", trainsDAO.getAllTrains());
    }

    @RequestMapping("/api/trains/**")
    public Map<String, List<TrainSchedule>> trainSchedules(HttpServletRequest request) {
        final String url = request.getRequestURI();
        final String trainName = URLDecoder.decode(url.substring("/api/trains/".length()));
        return Collections.singletonMap("schedules", trainsDAO.getTrainSchedules(trainName));
    }
}
