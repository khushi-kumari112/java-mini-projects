package com.moviebooking.online_movie_ticket_booking.service;

import com.moviebooking.online_movie_ticket_booking.dto.BookingRequestDto;
import com.moviebooking.online_movie_ticket_booking.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequestDto bookingRequest); // This must match exactly
    Booking getBookingById(Long id);
    List<Booking> getUserBookings(Long userId);
    String cancelBooking(Long bookingId);
    String generateBookingConfirmation(Long bookingId);
}