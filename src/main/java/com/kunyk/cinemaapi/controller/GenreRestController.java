package com.kunyk.cinemaapi.controller;

import com.kunyk.cinemaapi.dto.GenreDto;
import com.kunyk.cinemaapi.model.Genre;
import com.kunyk.cinemaapi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
public class GenreRestController {

    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDto> getGenres() {
        List<Genre> genres = genreService.getGenres();
        return genres.stream()
                .map(Genre::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GenreDto addGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreService.createGenre(genreDto);

        return genre.toDto();
    }

}
