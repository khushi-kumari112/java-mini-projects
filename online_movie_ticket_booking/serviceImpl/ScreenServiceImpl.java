
package com.moviebooking.online_movie_ticket_booking.serviceImpl;

import com.moviebooking.online_movie_ticket_booking.dto.ScreenDto;
import com.moviebooking.online_movie_ticket_booking.entity.Screen;
import com.moviebooking.online_movie_ticket_booking.entity.Theater;
import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.ScreenRepository;
import com.moviebooking.online_movie_ticket_booking.repository.TheaterRepository;
import com.moviebooking.online_movie_ticket_booking.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Screen addScreen(ScreenDto screenDto) {
        Theater theater = theaterRepository.findById(screenDto.getTheaterId())
                .orElseThrow(() -> new CustomException("Theater not found with id: " + screenDto.getTheaterId()));

        Screen screen = new Screen();
        screen.setName(screenDto.getName());
        screen.setTotalSeats(screenDto.getTotalSeats());
        screen.setTheater(theater);

        return screenRepository.save(screen);
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new CustomException("Screen not found with id: " + id));
    }

    @Override
    public List<Screen> getScreensByTheaterId(Long theaterId) {
        return screenRepository.findByTheaterId(theaterId);
    }

    @Override
    public void deleteScreen(Long id) {
        if (!screenRepository.existsById(id)) {
            throw new CustomException("Screen not found with id: " + id);
        }
        screenRepository.deleteById(id);
    }
}