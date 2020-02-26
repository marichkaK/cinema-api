package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieHallDto {

    private String name;
    private Integer horizontalSeatsNumber;
    private Integer verticalSeatsNumber;

    public MovieHallDto(String name) {
        this.name = name;
    }
}
