package com.kunyk.cinemaapi.repository;

import com.kunyk.cinemaapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByNameIn(List<String> names);

}
