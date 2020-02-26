package com.kunyk.cinemaapi.messaging.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketKafkaDto {
    private Long ticketId;
    private UserKafkaDto user;
    private MovieSessionPlaceDataKafkaDto movieSessionPlaceDataKafkaDto;
}

