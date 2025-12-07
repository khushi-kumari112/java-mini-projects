package com.moviebooking.online_movie_ticket_booking.entity;

import com.moviebooking.online_movie_ticket_booking.enums.PaymentMethod;
import com.moviebooking.online_movie_ticket_booking.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private double amount;
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus= PaymentStatus.PENDING;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
