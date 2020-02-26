package com.kunyk.cinemaapi.service;

import com.kunyk.cinemaapi.dto.MovieModelDto;
import com.kunyk.cinemaapi.model.Genre;
import com.kunyk.cinemaapi.model.Movie;
import com.kunyk.cinemaapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;

    @Autowired
    public MovieService(MovieRepository movieRepository, GenreService genreService) {
        this.movieRepository = movieRepository;
        this.genreService = genreService;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(MovieModelDto movieDto) {
        Movie movie = movieDto.toMovie();

        List<Genre> genres = genreService.findByNames(movieDto.getGenres());
        movie.setGenres(genres);

        return movieRepository.save(movie);
    }

    public Movie getMovie(Long id) {
        return movieRepository.getOne(id);
    }
}
