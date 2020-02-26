package com.kunyk.cinemaapi.service;

import com.kunyk.cinemaapi.model.MovieHallPlace;
import com.kunyk.cinemaapi.repository.MovieHallPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieHallPlaceService {

    private final MovieHallPlaceRepository movieHallPlaceRepository;

    @Autowired
    public MovieHallPlaceService(MovieHallPlaceRepository movieHallPlaceRepository) {
        this.movieHallPlaceRepository = movieHallPlaceRepository;
    }

    public List<MovieHallPlace> createMovieHallPlaces(List<MovieHallPlace> movieHallPlaces) {
        return movieHallPlaceRepository.saveAll(movieHallPlaces);
    }
}
