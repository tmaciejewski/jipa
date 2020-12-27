package japi;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.*;

@Service
public class TrainScraper {
    private static final Logger log = LoggerFactory.getLogger(TrainScraper.class);

    @Scheduled(fixedDelay=5000)
    public void updateTrains()
    {
        log.info("Updating trains...");
    }

}
