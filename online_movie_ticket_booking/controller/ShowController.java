
package com.moviebooking.online_movie_ticket_booking.controller;

import com.moviebooking.online_movie_ticket_booking.dto.ShowDto;
import com.moviebooking.online_movie_ticket_booking.entity.Show;
import com.moviebooking.online_movie_ticket_booking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<Show> addShow(@RequestBody ShowDto showDto) {
        Show show = showService.addShow(showDto);
        return ResponseEntity.ok(show);
    }

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        Show show = showService.getShowById(id);
        return ResponseEntity.ok(show);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieId(@PathVariable Long movieId) {
        List<Show> shows = showService.getShowsByMovieId(movieId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Show>> getShowsByTheaterIdAndDate(
            @PathVariable Long theaterId,
            @RequestParam String date) {

        // Parse date string to LocalDateTime (you might want to use a specific format)
        LocalDateTime showDate = LocalDateTime.parse(date);
        List<Show> shows = showService.getShowsByTheaterIdAndDate(theaterId, showDate);
        return ResponseEntity.ok(shows);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.ok("Show deleted successfully");
    }
}