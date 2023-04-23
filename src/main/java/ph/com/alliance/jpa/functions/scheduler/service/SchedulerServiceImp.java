package ph.com.alliance.jpa.functions.scheduler.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImp implements SchedulerService {

    @Scheduled(fixedRateString = "${scheduler.fixedRateMillis}")
    private void schedulePrint() {
        System.out.println("The time is now {" + LocalDateTime.now() + "}");
    }

}
