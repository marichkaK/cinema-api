package com.kunyk.cinemaapi.messaging.service;

import com.kunyk.cinemaapi.messaging.dto.TicketKafkaDto;
import com.kunyk.cinemaapi.messaging.stream.TicketStream;
import com.kunyk.cinemaapi.model.Ticket;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class TicketStreamService {

    private final TicketStream ticketStream;

    public TicketStreamService(TicketStream ticketStream) {
        this.ticketStream = ticketStream;
    }

    public boolean sendTicket(Ticket ticket) {
        TicketKafkaDto dto = TicketKafkaDto.builder()
                .ticketId(ticket.getId())
                .movieSessionPlaceDataKafkaDto(ticket.getMovieSessionPlaceData().toKafkaDto())
                .build();

        MessageChannel messageChannel = ticketStream.writeTickets();

        return messageChannel.send(MessageBuilder
                .withPayload(dto)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
