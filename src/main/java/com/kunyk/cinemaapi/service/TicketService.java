package com.kunyk.cinemaapi.service;

import com.kunyk.cinemaapi.dto.MovieDto;
import com.kunyk.cinemaapi.messaging.service.TicketStreamService;
import com.kunyk.cinemaapi.model.MovieSessionPlaceData;
import com.kunyk.cinemaapi.model.Status;
import com.kunyk.cinemaapi.model.Ticket;
import com.kunyk.cinemaapi.model.User;
import com.kunyk.cinemaapi.model.projection.MovieTotalProjection;
import com.kunyk.cinemaapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieSessionPlaceDataService movieSessionPlaceDataService;
    private final TicketStreamService ticketStreamService;
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            MovieSessionPlaceDataService movieSessionPlaceDataService,
            TicketStreamService ticketStreamService,
            SessionService sessionService,
            UserService userService) {

        this.ticketRepository = ticketRepository;
        this.movieSessionPlaceDataService = movieSessionPlaceDataService;
        this.ticketStreamService = ticketStreamService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Transactional
    public Ticket buyTicket(Long movieSessionPlaceDataId, User user) {
        MovieSessionPlaceData movieSessionPlaceData
                = movieSessionPlaceDataService.getMovieSessionPlaceData(movieSessionPlaceDataId);
        if (movieSessionPlaceData.getStatus() == Status.FREE) {

            Ticket ticket = ticketRepository.save(new Ticket(user, movieSessionPlaceData));
            movieSessionPlaceDataService.updateMovieSessionPlaceDataStatus(movieSessionPlaceDataId);
            ticketStreamService.sendTicket(ticket);

            return ticket;
        }
        return null;
    }

    @Transactional
    public void deleteAllTicketsAndRelatedDataByTime(LocalDate localDateTime) {
        List<Ticket> tickets = ticketRepository
                .getAllByMovieSessionPlaceDataSessionSessionTimeIsLessThan(localDateTime.atStartOfDay());
        Set<Long> sessionIds = new HashSet<>();
        for (Ticket t : tickets) {
            sessionIds.add(t.getMovieSessionPlaceData().getSession().getId());
        }
        ticketRepository.deleteAll(tickets);
        movieSessionPlaceDataService.deleteMovieSessionPlaceDatasBy(sessionIds);
        sessionService.deleteById(sessionIds);
    }

}
