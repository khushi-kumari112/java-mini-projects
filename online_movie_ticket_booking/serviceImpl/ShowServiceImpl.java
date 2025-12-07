package com.moviebooking.online_movie_ticket_booking.serviceImpl;



import com.moviebooking.online_movie_ticket_booking.dto.ShowDto;
import com.moviebooking.online_movie_ticket_booking.entity.Movie;
import com.moviebooking.online_movie_ticket_booking.entity.Screen;
import com.moviebooking.online_movie_ticket_booking.entity.Show;
import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.MovieRepository;
import com.moviebooking.online_movie_ticket_booking.repository.ScreenRepository;
import com.moviebooking.online_movie_ticket_booking.repository.ShowRepository;
import com.moviebooking.online_movie_ticket_booking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Show addShow(ShowDto showDto) {
        Movie movie = movieRepository.findById(showDto.getMovieId())
                .orElseThrow(() -> new CustomException("Movie not found with id: " + showDto.getMovieId()));

        Screen screen = screenRepository.findById(showDto.getScreenId())
                .orElseThrow(() -> new CustomException("Screen not found with id: " + showDto.getScreenId()));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setShowTime(showDto.getShowTime());
        show.setEndTime(showDto.getShowTime().plus(movie.getDuration()));

        return showRepository.save(show);
    }

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new CustomException("Show not found with id: " + id));
    }

    @Override
    public List<Show> getShowsByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new CustomException("Movie not found with id: " + movieId);
        }
        return showRepository.findByMovieIdAndShowTimeAfter(movieId, LocalDateTime.now());
    }

    @Override
    public List<Show> getShowsByTheaterIdAndDate(Long theaterId, LocalDateTime date) {
        LocalDateTime startOfDay = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = date.withHour(23).withMinute(59).withSecond(59);

        return showRepository.findByTheaterIdAndShowTimeBetween(theaterId, startOfDay, endOfDay);
    }

    @Override
    public void deleteShow(Long id) {
        if (!showRepository.existsById(id)) {
            throw new CustomException("Show not found with id: " + id);
        }
        showRepository.deleteById(id);
    }
}