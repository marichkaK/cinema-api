package com.kunyk.cinemaapi.service;

import com.kunyk.cinemaapi.dto.GenreDto;
import com.kunyk.cinemaapi.model.Genre;
import com.kunyk.cinemaapi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findByNames(List<String> names) {
        return genreRepository.findByNameIn(names);
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre createGenre(GenreDto genreDto) {
        return genreRepository.save(new Genre(genreDto.getName()));
    }
}
