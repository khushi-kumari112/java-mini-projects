package com.moviebooking.online_movie_ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moviebooking.online_movie_ticket_booking.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
