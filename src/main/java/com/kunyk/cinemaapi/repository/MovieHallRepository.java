package com.kunyk.cinemaapi.repository;

import com.kunyk.cinemaapi.model.MovieHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieHallRepository extends JpaRepository<MovieHall, Long> {

}
