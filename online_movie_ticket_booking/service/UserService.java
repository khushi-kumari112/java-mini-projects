package com.moviebooking.online_movie_ticket_booking.service;


import com.moviebooking.online_movie_ticket_booking.dto.UserDto;
import com.moviebooking.online_movie_ticket_booking.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(UserDto userDto);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    boolean validateUserCredentials(String email, String password);
}