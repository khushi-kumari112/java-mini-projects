package com.moviebooking.online_movie_ticket_booking.repository;
import com.moviebooking.online_movie_ticket_booking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    List<Show> findByMovieIdAndShowTimeAfter(Long movieId,LocalDateTime showTime);

   List<Show> findByTheaterIdAndShowTimeBetween(Long theaterId,LocalDateTime start,LocalDateTime end);
}
