package com.kunyk.cinemaapi.messaging.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TicketStream {
    String OUTPUT = "cinema-client-ticket-out";

    @Output(OUTPUT)
    MessageChannel writeTickets();
}
