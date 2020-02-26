package com.kunyk.cinemaapi.service;

import com.kunyk.cinemaapi.dto.NewSessionDto;
import com.kunyk.cinemaapi.model.*;
import com.kunyk.cinemaapi.repository.SessionRepository;
import com.kunyk.cinemaapi.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieService movieService;
    private final MovieHallService movieHallService;
    private final MovieSessionPlaceDataService movieSessionPlaceDataService;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          MovieService movieService,
                          MovieHallService movieHallService,
                          MovieSessionPlaceDataService movieSessionPlaceDataService) {
        this.sessionRepository = sessionRepository;
        this.movieService = movieService;
        this.movieHallService = movieHallService;
        this.movieSessionPlaceDataService = movieSessionPlaceDataService;
    }

    public List<Session> findAllSessions(Long movieId) {
        return sessionRepository.findAllByMovieId(movieId);
    }

    @Transactional
    public Session createSession(Long movieId, Long hallId, NewSessionDto newSessionDto) {
        Movie movie = movieService.getMovie(movieId);
        MovieHall movieHall = movieHallService.getMovieHall(hallId);

        Session session = new Session();
        session.setMovie(movie);
        session.setMovieHall(movieHall);
        session.setSessionTime(DateConverter.toLocalDateTime(newSessionDto.getSessionTime()));

        session = sessionRepository.save(session);

        List<MovieHallPlace> movieHallPlaces = movieHall.getMovieHallPlaces();
        Integer maxRowNumber = movieHallPlaces.stream()
                .max(Comparator.comparing(MovieHallPlace::getRowForSeat))
                .map(MovieHallPlace::getRowForSeat)
                .orElseThrow();

        List<MovieSessionPlaceData> movieSessionPlaceData = new ArrayList<>();
        for (int i = 0; i < movieHallPlaces.size(); i++) {
            MovieHallPlace movieHallPlace = movieHallPlaces.get(i);

            MovieSessionPlaceData placeData = new MovieSessionPlaceData(session, movieHallPlace);
            if (movieHallPlace.getRowForSeat().equals(maxRowNumber)) {
                placeData.setPrice(newSessionDto.getPriceForLastRow());
            } else {
                placeData.setPrice(newSessionDto.getBasePrice());
            }

            movieSessionPlaceData.add(placeData);
        }

        movieSessionPlaceData = movieSessionPlaceDataService.addMovieSessionPlaceData(movieSessionPlaceData);
        session.setMovieSessionPlaceData(movieSessionPlaceData);

        return session;
    }

    public void deleteById(Set<Long> ids) {
        sessionRepository.deleteByIdIn(ids);
    }

    public Session getSession(Long id) {
        return sessionRepository.getOne(id);
    }
}
