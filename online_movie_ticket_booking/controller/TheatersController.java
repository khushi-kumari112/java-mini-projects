
package com.moviebooking.online_movie_ticket_booking.controller;

import com.moviebooking.online_movie_ticket_booking.dto.TheaterDto;
import com.moviebooking.online_movie_ticket_booking.entity.Theater;
import com.moviebooking.online_movie_ticket_booking.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDto theaterDto) {
        Theater theater = theaterService.addTheater(theaterDto);
        return ResponseEntity.ok(theater);
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        Theater theater = theaterService.getTheaterById(id);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theater>> getTheatersByCity(@PathVariable String city) {
        List<Theater> theaters = theaterService.getTheatersByCity(city);
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Theater>> searchTheaters(@RequestParam String location) {
        List<Theater> theaters = theaterService.searchTheatersByLocation(location);
        return ResponseEntity.ok(theaters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.ok("Theater deleted successfully");
    }
}