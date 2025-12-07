package com.moviebooking.online_movie_ticket_booking.repository;

import com.moviebooking.online_movie_ticket_booking.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowId(Long showId);
    List<ShowSeat> findByBookingId(Long bookingId); // ADD THIS
    List<ShowSeat> findByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);
}