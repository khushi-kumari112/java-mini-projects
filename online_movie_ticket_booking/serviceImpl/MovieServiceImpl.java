package com.moviebooking.online_movie_ticket_booking.serviceImpl;


// MovieServiceImpl.java



import com.moviebooking.online_movie_ticket_booking.entity.Movie;
import com.moviebooking.online_movie_ticket_booking.dto.MovieDto;

import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.MovieRepository;
import com.moviebooking.online_movie_ticket_booking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        // Map fields from movieDto to movie
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDuration(movieDto.getDuration());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDirector(movieDto.getDirector());
        movie.setCast(movieDto.getCast());

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new CustomException("Movie not found with id: " + id));
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new CustomException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }
}


