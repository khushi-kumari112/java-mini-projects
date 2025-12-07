package com.moviebooking.online_movie_ticket_booking.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long bookingId;
    private String paymentMethod;
    private String cardNumber;
    private  String cardHolderName;
    private String expiryData;
    private String cvv;
}
