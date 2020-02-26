package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.FullSessionDto;
import com.kunyk.cinemaapi.dto.MovieSessionPlaceDataDto;
import com.kunyk.cinemaapi.dto.SessionDto;
import com.kunyk.cinemaapi.messaging.dto.SessionKafkaDto;
import com.kunyk.cinemaapi.util.DateConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Session")
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_hall_id")
    private MovieHall movieHall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "session_time", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime sessionTime;

    @OneToMany(mappedBy = "session")
    private List<MovieSessionPlaceData> movieSessionPlaceData;

    public SessionDto toDto() {
        return SessionDto.builder()
                .id(id)
                .movie(movie.toDto())
                .sessionTime(DateConverter.convertToDateViaSqlTimestamp(sessionTime))
                .build();
    }

    public SessionKafkaDto toKafkaDto(){
        return SessionKafkaDto.builder()
                .id(id)
                .movieKafkaDto(movie.toKafkaDto())
                .build();
    }

    public FullSessionDto toFullSessionDto() {
        return FullSessionDto.builder()
                .id(id)
                .movie(movie.toDto())
                .movieHall(movieHall.toDto())
                .sessionTime(DateConverter.convertToDateViaSqlTimestamp(sessionTime))
                .placeDataList(getMovieSessionPlaceDataList())
                .build();
    }

    private List<MovieSessionPlaceDataDto> getMovieSessionPlaceDataList() {
        return movieSessionPlaceData.stream()
                .map(MovieSessionPlaceData::toDto)
                .collect(Collectors.toList());
    }
}
