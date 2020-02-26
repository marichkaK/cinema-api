package com.kunyk.cinemaapi.repository;

import com.kunyk.cinemaapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByMovieId(Long id);

    void deleteByIdIn(Set<Long> ids);

}
