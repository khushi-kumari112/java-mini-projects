
package com.moviebooking.online_movie_ticket_booking.dto;

import lombok.Data;

@Data
public class ScreenDto {
    private String name;
    private int totalSeats;
    private Long theaterId;
}