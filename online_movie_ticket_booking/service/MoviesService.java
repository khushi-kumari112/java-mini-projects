package com.moviebooking.online_movie_ticket_booking.service;
import com.moviebooking.online_movie_ticket_booking.entity.Movie;
import com.moviebooking.online_movie_ticket_booking.dto.MovieDto;

import java.util.List;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    List<Movie> searchMoviesByTitle(String title);
    List<Movie> getMoviesByGenre(String genre);
    void deleteMovie(Long id);
}

