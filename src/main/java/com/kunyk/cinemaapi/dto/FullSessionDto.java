package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullSessionDto {

    private Long id;
    private MovieDto movie;
    private MovieHallDto movieHall;
    private List<MovieSessionPlaceDataDto> placeDataList;
    private Date sessionTime;
}