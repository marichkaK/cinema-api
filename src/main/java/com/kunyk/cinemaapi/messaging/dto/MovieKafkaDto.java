package com.kunyk.cinemaapi.messaging.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieKafkaDto {
    private String name;
    private Integer duration;
    private Integer minAge;
    private String country;
    private List<String> genres;
}
