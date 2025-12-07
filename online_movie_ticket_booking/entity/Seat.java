package com.moviebooking.online_movie_ticket_booking.entity;

import com.moviebooking.online_movie_ticket_booking.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private double price;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
