package com.moviebooking.online_movie_ticket_booking.repository;

import com.moviebooking.online_movie_ticket_booking.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCity(String city);
    List<Theater> findByLocationContainingIgnoreCase(String location);
}

