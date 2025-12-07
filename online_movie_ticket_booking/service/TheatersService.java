package com.moviebooking.online_movie_ticket_booking.service;
import com.moviebooking.online_movie_ticket_booking.entity.Theater;
import com.moviebooking.online_movie_ticket_booking.dto.TheaterDto;
import java.util.List;
public interface TheaterService {
    Theater addTheater(TheaterDto theaterDto);
    List<Theater> getAllTheaters();
    Theater getTheaterById(Long id);
    List<Theater> getTheatersByCity(String city);
    List<Theater> searchTheatersByLocation(String location);
    void deleteTheater(Long id);
}
