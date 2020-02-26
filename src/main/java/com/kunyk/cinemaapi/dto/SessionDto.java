package com.kunyk.cinemaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {

    private Long id;
    private MovieDto movie;
    private Date sessionTime;
}
