package com.kunyk.cinemaapi.messaging.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserKafkaDto {

    private Long id;
    private Integer age;
}
