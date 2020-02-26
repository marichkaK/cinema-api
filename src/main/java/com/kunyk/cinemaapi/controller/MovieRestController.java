package com.kunyk.cinemaapi.controller;

import com.kunyk.cinemaapi.dto.MovieDto;
import com.kunyk.cinemaapi.dto.MovieModelDto;
import com.kunyk.cinemaapi.model.Movie;
import com.kunyk.cinemaapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieService.findAllMovies();
        return movies.stream()
                .map(Movie::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovieModelDto getMovie(@PathVariable Long id) {
        return movieService.getMovie(id).toMovieDto();
    }

    @PostMapping
    public MovieModelDto createMovie(@RequestBody MovieModelDto movieDto) {
        Movie movie = movieService.createMovie(movieDto);

        return movie.toMovieDto();
    }
}
