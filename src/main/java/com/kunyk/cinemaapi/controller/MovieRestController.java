package com.kunyk.cinemaapi.controller;

import com.kunyk.cinemaapi.dto.MovieDto;
import com.kunyk.cinemaapi.dto.MovieModelDto;
import com.kunyk.cinemaapi.model.Movie;
import com.kunyk.cinemaapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getMovies() {
        List<Movie> movies = movieService.findAllMovies();
        List<MovieDto> dtos = movies.stream()
                .map(Movie::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        MovieModelDto dto = movieService.getMovie(id).toMovieDto();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieModelDto movieDto) {
        Movie movie = movieService.createMovie(movieDto);
        return ResponseEntity.status(201).body(movie.toMovieDto());
    }
}
