package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.MovieSessionPlaceDataDto;
import com.kunyk.cinemaapi.messaging.dto.MovieSessionPlaceDataKafkaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "MovieSessionPlaceData")
@Table(name = "movie_session_place_data")
public class MovieSessionPlaceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "movie_hall_place_id")
    private MovieHallPlace movieHallPlace;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public MovieSessionPlaceData(Session session, MovieHallPlace movieHallPlace) {
        this.session = session;
        this.movieHallPlace = movieHallPlace;
    }

    @PrePersist
    public void init() {
        this.status = Status.FREE;
    }

    public MovieSessionPlaceDataDto toDto() {
        return MovieSessionPlaceDataDto.builder()
                .id(id)
                .place(movieHallPlace.toDto())
                .price(price)
                .status(status.toString())
                .build();
    }


    public MovieSessionPlaceDataKafkaDto toKafkaDto() {
        return MovieSessionPlaceDataKafkaDto.builder()
                .movieSessionPlaceDataId(id)
                .price(price)
                .sessionKafkaDto(session.toKafkaDto())
                .build();
    }
}
