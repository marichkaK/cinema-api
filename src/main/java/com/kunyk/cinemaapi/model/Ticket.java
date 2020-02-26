package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.TicketDto;
import com.kunyk.cinemaapi.messaging.dto.TicketKafkaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_session_place_id")
    private MovieSessionPlaceData movieSessionPlaceData;

    public Ticket(User user, MovieSessionPlaceData movieSessionPlaceData) {
        this.user = user;
        this.movieSessionPlaceData = movieSessionPlaceData;
    }

    public TicketDto toDto() {
        return TicketDto.builder()
                .id(id)
                .movieSessionPlaceDataId(movieSessionPlaceData.getId())
                .build();
    }

    public TicketKafkaDto toKafkaDto() {
        return TicketKafkaDto.builder()
                .ticketId(id)
                .movieSessionPlaceDataKafkaDto(movieSessionPlaceData.toKafkaDto())
                .build();
    }
}
