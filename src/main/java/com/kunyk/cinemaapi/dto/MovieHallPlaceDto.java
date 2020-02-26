package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieHallPlaceDto {

    private Integer rowForSeat;
    private Integer seat;
}
