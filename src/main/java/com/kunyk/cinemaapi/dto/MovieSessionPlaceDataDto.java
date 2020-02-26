package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSessionPlaceDataDto {

    private Long id;
    private MovieHallPlaceDto place;
    private Integer price;
    private String status;
}

