package com.kunyk.cinemaapi.model.projection;

import com.kunyk.cinemaapi.model.Movie;

public class MovieTotalProjection {
    private Movie movie;

    private Long totalTickets;

    public MovieTotalProjection(Movie movie, Long totalTickets) {
        this.movie = movie;
        this.totalTickets = totalTickets;
    }

    public Movie getMovie() {
        return movie;
    }

    public Long getTotalTickets() {
        return totalTickets;
    }
}
