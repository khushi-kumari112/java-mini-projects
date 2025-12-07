package com.moviebooking.online_movie_ticket_booking.service;


import com.moviebooking.online_movie_ticket_booking.dto.PaymentRequestDto;
import com.moviebooking.online_movie_ticket_booking.entity.Payment;

public interface PaymentService {
    String processPayment(PaymentRequestDto paymentRequest);
    Payment getPaymentById(Long id);
    Payment getPaymentByBookingId(Long bookingId);
    String getPaymentStatus(Long bookingId);
    String refundPayment(Long bookingId);
}