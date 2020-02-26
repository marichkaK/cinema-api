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
}
