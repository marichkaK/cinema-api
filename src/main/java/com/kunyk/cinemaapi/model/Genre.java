package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.dto.GenreDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Genre")
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

    public Genre(String name) {
        this.name = name;
    }

    public GenreDto toDto() {
        return new GenreDto(name);
    }
}