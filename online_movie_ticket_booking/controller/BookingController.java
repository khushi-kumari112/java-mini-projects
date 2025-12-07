package com.moviebooking.online_movie_ticket_booking.controller;



import com.moviebooking.online_movie_ticket_booking.dto.BookingRequestDto;
import com.moviebooking.online_movie_ticket_booking.entity.Booking;
import com.moviebooking.online_movie_ticket_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDto bookingRequest) {
        Booking booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        String message = bookingService.cancelBooking(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}/confirmation")
    public ResponseEntity<String> getBookingConfirmation(@PathVariable Long id) {
        String confirmation = bookingService.generateBookingConfirmation(id);
        return ResponseEntity.ok(confirmation);
    }
}