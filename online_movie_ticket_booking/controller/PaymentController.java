package com.moviebooking.online_movie_ticket_booking.controller;



import com.moviebooking.online_movie_ticket_booking.dto.PaymentRequestDto;
import com.moviebooking.online_movie_ticket_booking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequestDto paymentRequest) {
        String result = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{bookingId}/status")
    public ResponseEntity<String> getPaymentStatus(@PathVariable Long bookingId) {
        String status = paymentService.getPaymentStatus(bookingId);
        return ResponseEntity.ok(status);
    }
}