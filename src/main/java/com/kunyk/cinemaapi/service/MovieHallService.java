package com.kunyk.cinemaapi.service;


import com.kunyk.cinemaapi.dto.MovieHallDto;
import com.kunyk.cinemaapi.model.MovieHall;
import com.kunyk.cinemaapi.model.MovieHallPlace;
import com.kunyk.cinemaapi.repository.MovieHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieHallService {

    private final MovieHallRepository movieHallRepository;
    private final MovieHallPlaceService movieHallPlaceService;

    @Autowired
    public MovieHallService(MovieHallRepository movieHallRepository, MovieHallPlaceService movieHallPlaceService) {
        this.movieHallRepository = movieHallRepository;
        this.movieHallPlaceService = movieHallPlaceService;
    }

    public MovieHall getMovieHall(Long id) {
        return movieHallRepository.getOne(id);
    }

    @Transactional
    public MovieHall createMovieHall(MovieHallDto movieHallDto) {
        MovieHall movieHall = movieHallRepository.save(new MovieHall(movieHallDto.getName()));

        List<MovieHallPlace> movieHallPlaces = new ArrayList<>();
        for (int i = 0; i < movieHallDto.getHorizontalSeatsNumber(); i++) {
            for (int j = 0; j < movieHallDto.getVerticalSeatsNumber(); j++) {
                movieHallPlaces.add(new MovieHallPlace(movieHall, i, j));
            }
        }

        movieHallPlaces = movieHallPlaceService.createMovieHallPlaces(movieHallPlaces);
        movieHall.setMovieHallPlaces(movieHallPlaces);

        return movieHallRepository.save(movieHall);
    }
}
