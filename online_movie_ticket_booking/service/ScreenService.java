
package com.moviebooking.online_movie_ticket_booking.service;

import com.moviebooking.online_movie_ticket_booking.dto.ScreenDto;
import com.moviebooking.online_movie_ticket_booking.entity.Screen;
import java.util.List;

public interface ScreenService {
    Screen addScreen(ScreenDto screenDto);
    List<Screen> getAllScreens();
    Screen getScreenById(Long id);
    List<Screen> getScreensByTheaterId(Long theaterId);
    void deleteScreen(Long id);
}