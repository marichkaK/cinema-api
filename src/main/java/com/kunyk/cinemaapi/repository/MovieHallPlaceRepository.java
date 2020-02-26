package com.kunyk.cinemaapi.repository;

import com.kunyk.cinemaapi.model.MovieHallPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieHallPlaceRepository extends JpaRepository<MovieHallPlace, Long> {

}
