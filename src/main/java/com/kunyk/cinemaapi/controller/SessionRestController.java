package com.kunyk.cinemaapi.controller;

import com.kunyk.cinemaapi.dto.FullSessionDto;
import com.kunyk.cinemaapi.dto.NewSessionDto;
import com.kunyk.cinemaapi.dto.SessionDto;
import com.kunyk.cinemaapi.model.Session;
import com.kunyk.cinemaapi.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SessionRestController {

    private final SessionService sessionService;

    @Autowired
    public SessionRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/movies/{movieId}/sessions")
    public List<SessionDto> getSessionsByMovie(@PathVariable Long movieId) {
        List<Session> sessions = sessionService.findAllSessions(movieId);

        return sessions.stream()
                .map(Session::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/sessions/{id}")
    public FullSessionDto getSessionById(@PathVariable Long id) {
        Session session = sessionService.getSession(id);

        return session.toFullSessionDto();
    }

    @PostMapping("/movies/{movieId}/halls/{hallId}/sessions")
    public FullSessionDto createMovie(
            @PathVariable Long movieId,
            @PathVariable Long hallId,
            @RequestBody NewSessionDto newSessionDto) {

        Session session = sessionService.createSession(movieId, hallId, newSessionDto);

        return session.toFullSessionDto();
    }

}
