package com.kunyk.cinemaapi.scheduler;

import com.kunyk.cinemaapi.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ScheduledTasks {
    private final TicketService ticketService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(TicketService sessionService) {
        this.ticketService = sessionService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteObsoleteDataOncePerDay() {
        ticketService.deleteAllTicketsAndRelatedDataByTime(LocalDate.now());
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}

