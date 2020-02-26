package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;

    private String name;

    private Date startDate;

    public MovieDto(Long id, String name, Date startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }
}
