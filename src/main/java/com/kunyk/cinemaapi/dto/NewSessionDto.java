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
public class NewSessionDto {

    private Date sessionTime;
    private Integer basePrice;
    private Integer priceForLastRow;
}
