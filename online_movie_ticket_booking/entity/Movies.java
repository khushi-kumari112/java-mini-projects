package com.moviebooking.online_movie_ticket_booking.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moviebooking.online_movie_ticket_booking.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Duration duration;
    private String description;
    private String language;
    private String director;
    private String cast;
    private LocalDate releaseDate;
    private Double rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Show> shows;

}
