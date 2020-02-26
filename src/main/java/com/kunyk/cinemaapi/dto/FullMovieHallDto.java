package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullMovieHallDto {

    private String name;
    private List<MovieHallPlaceDto> movieHallPlaces;
}