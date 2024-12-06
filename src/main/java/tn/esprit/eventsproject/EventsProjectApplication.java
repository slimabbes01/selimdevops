package tn.esprit.eventsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EventsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsProjectApplication.class, args);
    }

}
