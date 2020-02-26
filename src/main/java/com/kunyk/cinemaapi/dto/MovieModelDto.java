package com.kunyk.cinemaapi.dto;

import com.kunyk.cinemaapi.model.Format;
import com.kunyk.cinemaapi.model.Movie;
import com.kunyk.cinemaapi.util.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieModelDto {

    private String name;
    private Integer duration;
    private String format;
    private Integer minAge;
    private Integer year;
    private String country;
    private String description;
    private Date startDate;
    private Date endDate;
    private String logoPath;
    private List<String> genres;

    public Movie toMovie() {
        return Movie.builder()
                .name(name)
                .duration(duration)
                .format(Format.valueOf(format))
                .minAge(minAge)
                .year(year)
                .country(country)
                .description(description)
                .startDate(DateConverter.toLocalDateTime(startDate))
                .endDate(DateConverter.toLocalDateTime(endDate))
                .logoPath(logoPath)
                .build();
    }
}
