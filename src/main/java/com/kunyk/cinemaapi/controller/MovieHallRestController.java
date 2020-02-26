package com.kunyk.cinemaapi.controller;

import com.kunyk.cinemaapi.dto.FullMovieHallDto;
import com.kunyk.cinemaapi.dto.MovieHallDto;
import com.kunyk.cinemaapi.model.MovieHall;
import com.kunyk.cinemaapi.service.MovieHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie_halls")
public class MovieHallRestController {

    private final MovieHallService movieHallService;

    @Autowired
    public MovieHallRestController(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    @PostMapping
    public FullMovieHallDto createMovieHall(@RequestBody MovieHallDto movieHallDto) {
        MovieHall movieHall = movieHallService.createMovieHall(movieHallDto);

        return movieHall.toFullDto();
    }
}
