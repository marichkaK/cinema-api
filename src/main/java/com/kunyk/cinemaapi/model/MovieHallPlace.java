package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.MovieHallPlaceDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "MovieHallPlace")
@Table(name = "movie_hall_place")
public class MovieHallPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_hall_id")
    private MovieHall movieHall;

    @Column(name = "row_for_seat", nullable = false)
    private Integer rowForSeat;

    @Column(name = "seat", nullable = false)
    private Integer seat;

    public MovieHallPlace(MovieHall movieHall, Integer rowForSeat, Integer seat) {
        this.movieHall = movieHall;
        this.rowForSeat = rowForSeat;
        this.seat = seat;
    }

    public MovieHallPlaceDto toDto() {
        return MovieHallPlaceDto.builder()
                .rowForSeat(rowForSeat)
                .seat(seat)
                .build();
    }
}
