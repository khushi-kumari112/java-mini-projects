package com.moviebooking.online_movie_ticket_booking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDto {
    private Long id;
    private String bookingReference;
    private String movieTitle;
    private String theaterName;
    private LocalDateTime showTime;
    private List<String> seatNumbers;
    private Double totalAmount;
    private String status;
    private String message;
}
