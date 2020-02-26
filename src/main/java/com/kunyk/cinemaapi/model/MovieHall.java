package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.FullMovieHallDto;
import com.kunyk.cinemaapi.dto.MovieHallDto;
import com.kunyk.cinemaapi.dto.MovieHallPlaceDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "MovieHall")
@Table(name = "movie_hall")
public class MovieHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "movieHall")
    private List<MovieHallPlace> movieHallPlaces;

    public MovieHall(String name) {
        this.name = name;
    }

    public MovieHallDto toDto() {
        return new MovieHallDto(name);
    }

    public FullMovieHallDto toFullDto() {
        List<MovieHallPlaceDto> hallPlaceDtos = movieHallPlaces.stream()
                .map(MovieHallPlace::toDto)
                .collect(Collectors.toList());

        return new FullMovieHallDto(name, hallPlaceDtos);
    }
}
