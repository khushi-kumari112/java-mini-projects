package com.moviebooking.online_movie_ticket_booking.serviceImpl;

import com.moviebooking.online_movie_ticket_booking.dto.BookingRequestDto;
import com.moviebooking.online_movie_ticket_booking.entity.*;
import com.moviebooking.online_movie_ticket_booking.enums.BookingStatus;
import com.moviebooking.online_movie_ticket_booking.enums.PaymentMethod;
import com.moviebooking.online_movie_ticket_booking.enums.PaymentStatus;
import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.*;
import com.moviebooking.online_movie_ticket_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Booking createBooking(BookingRequestDto bookingRequest) {
        // Validate user
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new CustomException("User not found with id: " + bookingRequest.getUserId()));

        // Validate show
        Show show = showRepository.findById(bookingRequest.getShowId())
                .orElseThrow(() -> new CustomException("Show not found with id: " + bookingRequest.getShowId()));

        // Validate seats availability
        List<ShowSeat> showSeats = showSeatRepository.findAllById(bookingRequest.getSeatIds());

        // Check if all seats were found
        if (showSeats.size() != bookingRequest.getSeatIds().size()) {
            throw new CustomException("One or more seats not found");
        }

        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.isAvailable()) {
                throw new CustomException("Seat " + showSeat.getSeat().getSeatNumber() + " is not available");
            }
            if (!showSeat.getShow().getId().equals(show.getId())) {
                throw new CustomException("Seat does not belong to this show");
            }
        }

        // Calculate total amount
        double totalAmount = showSeats.stream()
                .mapToDouble(showSeat -> showSeat.getSeat().getPrice())
                .sum();

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(showSeats.size());
        booking.setTotalAmount(totalAmount);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingReference(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Booking savedBooking = bookingRepository.save(booking);

        // Update show seats
        for (ShowSeat showSeat : showSeats) {
            showSeat.setAvailable(false);
            showSeat.setBooking(savedBooking);
            showSeatRepository.save(showSeat);
        }

        // Create pending payment
        Payment payment = new Payment();
        payment.setBooking(savedBooking);
        payment.setAmount(totalAmount);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        // Handle potential invalid payment method
        try {
            payment.setPaymentMethod(PaymentMethod.valueOf(bookingRequest.getPaymentMethod().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new CustomException("Invalid payment method: " + bookingRequest.getPaymentMethod());
        }

        paymentRepository.save(payment);

        return savedBooking;
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found with id: " + id));
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException("User not found with id: " + userId);
        }
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new CustomException("Booking not found with id: " + bookingId));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new CustomException("Booking is already cancelled");
        }

        // Check if show time is more than 2 hours away
        if (booking.getShow().getShowTime().minusHours(2).isBefore(LocalDateTime.now())) {
            throw new CustomException("Cannot cancel booking less than 2 hours before show time");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Free up the seats
        List<ShowSeat> showSeats = showSeatRepository.findByBookingId(bookingId);
        for (ShowSeat showSeat : showSeats) {
            showSeat.setAvailable(true);
            showSeat.setBooking(null);
            showSeatRepository.save(showSeat);
        }

        // Refund payment if already paid
        Payment payment = paymentRepository.findByBookingId(bookingId);
        if (payment != null && payment.getPaymentStatus() == PaymentStatus.COMPLETED) {
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);

            return "Booking cancelled successfully. Refund has been initiated.";
        }

        return "Booking cancelled successfully.";
    }

    @Override
    public String generateBookingConfirmation(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new CustomException("Booking not found with id: " + bookingId));

        List<ShowSeat> showSeats = showSeatRepository.findByBookingId(bookingId);

        if (showSeats.isEmpty()) {
            throw new CustomException("No seats found for this booking");
        }

        String seats = showSeats.stream()
                .map(showSeat -> showSeat.getSeat().getSeatNumber())
                .collect(Collectors.joining(", "));

        return String.format(
                "Booking Confirmation\n" +
                        "Reference: %s\n" +
                        "Movie: %s\n" +
                        "Theater: %s, %s\n" +
                        "Screen: %s\n" +
                        "Show Time: %s\n" +
                        "Seats: %s\n" +
                        "Total Amount: ₹%.2f\n" +
                        "Status: %s",
                booking.getBookingReference(),
                booking.getShow().getMovie().getTitle(),
                booking.getShow().getScreen().getTheater().getName(),
                booking.getShow().getScreen().getTheater().getLocation(),
                booking.getShow().getScreen().getName(),
                booking.getShow().getShowTime().toString(),
                seats,
                booking.getTotalAmount(),
                booking.getStatus().toString()
        );
    }
}