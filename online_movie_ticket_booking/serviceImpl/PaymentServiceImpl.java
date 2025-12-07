package com.moviebooking.online_movie_ticket_booking.serviceImpl;



import com.moviebooking.online_movie_ticket_booking.dto.PaymentRequestDto;
import com.moviebooking.online_movie_ticket_booking.entity.Booking;
import com.moviebooking.online_movie_ticket_booking.entity.Payment;
import com.moviebooking.online_movie_ticket_booking.enums.PaymentStatus;
import com.moviebooking.online_movie_ticket_booking.exception.CustomException;
import com.moviebooking.online_movie_ticket_booking.repository.BookingRepository;
import com.moviebooking.online_movie_ticket_booking.repository.PaymentRepository;
import com.moviebooking.online_movie_ticket_booking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public String processPayment(PaymentRequestDto paymentRequest) {
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
                .orElseThrow(() -> new CustomException("Booking not found with id: " + paymentRequest.getBookingId()));

        Payment payment = paymentRepository.findByBookingId(paymentRequest.getBookingId());
        if (payment == null) {
            throw new CustomException("Payment not found for booking id: " + paymentRequest.getBookingId());
        }

        // Simulate payment processing
        boolean paymentSuccess = simulatePaymentProcessing(paymentRequest);

        if (paymentSuccess) {
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentTime(LocalDateTime.now());

            booking.setStatus(com.moviebooking.online_movie_ticket_booking.enums.BookingStatus.CONFIRMED);

            paymentRepository.save(payment);
            bookingRepository.save(booking);

            return "Payment successful! Transaction ID: " + payment.getTransactionId();
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);

            return "Payment failed. Please try again with different payment method.";
        }
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Payment not found with id: " + id));
    }

    @Override
    public Payment getPaymentByBookingId(Long bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId);
        if (payment == null) {
            throw new CustomException("Payment not found for booking id: " + bookingId);
        }
        return payment;
    }

    @Override
    public String getPaymentStatus(Long bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId);
        if (payment == null) {
            throw new CustomException("Payment not found for booking id: " + bookingId);
        }
        return payment.getPaymentStatus().toString();
    }

    @Override
    public String refundPayment(Long bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId);
        if (payment == null) {
            throw new CustomException("Payment not found for booking id: " + bookingId);
        }

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new CustomException("Cannot refund payment that is not completed");
        }

        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);

        return "Refund processed successfully for booking ID: " + bookingId;
    }

    // Simulate payment processing (in real app, integrate with payment gateway)
    private boolean simulatePaymentProcessing(PaymentRequestDto paymentRequest) {
        // Simulate 90% success rate for demo purposes
        return Math.random() > 0.1;
    }
}