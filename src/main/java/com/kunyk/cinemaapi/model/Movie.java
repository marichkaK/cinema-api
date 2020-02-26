package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.MovieDto;
import com.kunyk.cinemaapi.dto.MovieModelDto;
import com.kunyk.cinemaapi.messaging.dto.MovieKafkaDto;
import com.kunyk.cinemaapi.util.DateConverter;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kunyk.cinemaapi.util.DateConverter.convertToDateViaSqlTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity(name = "Movie")
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "format", length = 20)
    private Format format;

    @Column(name = "minAge", nullable = false)
    private Integer minAge;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country")
    private String country;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "startDate")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime startDate;

    @Column(name = "endDate")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime endDate;

    @Column(name = "logoPath")
    private String logoPath;

    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public MovieDto toDto() {
        return new MovieDto(id, name, convertToDateViaSqlTimestamp(startDate));
    }

    public MovieModelDto toMovieDto() {
        return MovieModelDto.builder()
                .name(name)
                .duration(duration)
                .format(format.toString())
                .minAge(minAge)
                .year(year)
                .country(country)
                .description(description)
                .startDate(DateConverter.convertToDateViaSqlTimestamp(startDate))
                .endDate(DateConverter.convertToDateViaSqlTimestamp(endDate))
                .logoPath(logoPath)
                .genres(genres.stream().map(Genre::getName).collect(Collectors.toList()))
                .build();
    }

    public MovieKafkaDto toKafkaDto() {
        List<String> genreTitles = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        return MovieKafkaDto.builder()
                .name(name)
                .country(country)
                .duration(duration)
                .minAge(minAge)
                .genres(genreTitles)
                .build();
    }
}