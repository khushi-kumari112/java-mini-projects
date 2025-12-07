package com.moviebooking.online_movie_ticket_booking.serviceImpl;



import com.moviebooking.online_movie_ticket_booking.dto.TheaterDto;
import com.moviebooking.online_movie_ticket_booking.entity.Theater;
import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.TheaterRepository;
import com.moviebooking.online_movie_ticket_booking.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater addTheater(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setName(theaterDto.getName());
        theater.setLocation(theaterDto.getLocation());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreens(theaterDto.getTotalScreens());

        return theaterRepository.save(theater);
    }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new CustomException("Theater not found with id: " + id));
    }

    @Override
    public List<Theater> getTheatersByCity(String city) {
        return theaterRepository.findByCity(city);
    }

    @Override
    public List<Theater> searchTheatersByLocation(String location) {
        return theaterRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public void deleteTheater(Long id) {
        if (!theaterRepository.existsById(id)) {
            throw new CustomException("Theater not found with id: " + id);
        }
        theaterRepository.deleteById(id);
    }
}