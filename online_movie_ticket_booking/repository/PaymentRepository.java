
package com.moviebooking.online_movie_ticket_booking.repository;

import com.moviebooking.online_movie_ticket_booking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTransactionId(String transactionId);
    Payment findByBookingId(Long bookingId);
}