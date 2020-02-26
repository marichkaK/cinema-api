package com.kunyk.cinemaapi.repository;

import com.kunyk.cinemaapi.model.MovieSessionPlaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieSessionPlaceDataRepository extends JpaRepository<MovieSessionPlaceData, Long> {
    void deleteAllBySessionIdIn(Set<Long> ids);
}
