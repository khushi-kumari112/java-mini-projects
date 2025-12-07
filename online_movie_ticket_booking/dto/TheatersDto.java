package com.moviebooking.online_movie_ticket_booking.dto;

import lombok.Data;

@Data
public class TheaterDto {
    private String name;
    private String location;
    private String city;
    private int totalScreens;
}
