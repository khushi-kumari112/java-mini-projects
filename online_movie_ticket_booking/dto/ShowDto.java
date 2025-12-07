package com.moviebooking.online_movie_ticket_booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDto {
    private Long movieId;
    private Long screenId;
    private LocalDateTime showTime;


}
