package com.moviebooking.online_movie_ticket_booking.service;


import com.moviebooking.online_movie_ticket_booking.dto.ShowDto;
import com.moviebooking.online_movie_ticket_booking.entity.Show;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowService {
    Show addShow(ShowDto showDto);
    List<Show> getAllShows();
    Show getShowById(Long id);
    List<Show> getShowsByMovieId(Long movieId);
    List<Show> getShowsByTheaterIdAndDate(Long theaterId, LocalDateTime date);
    void deleteShow(Long id);
}