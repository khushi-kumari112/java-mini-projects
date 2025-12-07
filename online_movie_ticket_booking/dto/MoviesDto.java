package com.moviebooking.online_movie_ticket_booking.dto;
import com.moviebooking.online_movie_ticket_booking.enums.Genre;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private Genre genre;
    private Duration duration;
    private String description;
    private String language;
    private String director;
    private String cast;
    private LocalDate releaseDate;
    private Double rating;
}
