package com.moviebooking.online_movie_ticket_booking.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
