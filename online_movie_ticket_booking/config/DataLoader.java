package com.moviebooking.online_movie_ticket_booking.config;

import com.moviebooking.online_movie_ticket_booking.entity.*;
import com.moviebooking.online_movie_ticket_booking.enums.Genre;
import com.moviebooking.online_movie_ticket_booking.enums.SeatType;
import com.moviebooking.online_movie_ticket_booking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load initial data if database is empty
        if (movieRepository.count() == 0) {
            loadInitialData();
        }
    }

    private void loadInitialData() {
        // Create movies
        Movie movie1 = new Movie();
        movie1.setTitle("Avengers: Endgame");
        movie1.setGenre(Genre.ACTION);
        movie1.setDuration(Duration.ofMinutes(181));
        movie1.setDescription("The Avengers assemble once more to reverse Thanos' actions.");
        movie1.setLanguage("English");
        movie1.setDirector("Anthony and Joe Russo");
        movie1.setCast("Robert Downey Jr., Chris Evans, Mark Ruffalo");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("The Dark Knight");
        movie2.setGenre(Genre.ACTION);
        movie2.setDuration(Duration.ofMinutes(152));
        movie2.setDescription("Batman sets out to dismantle the remaining criminal organizations.");
        movie2.setLanguage("English");
        movie2.setDirector("Christopher Nolan");
        movie2.setCast("Christian Bale, Heath Ledger, Aaron Eckhart");
        movieRepository.save(movie2);

        // Create theaters
        Theater theater1 = new Theater();
        theater1.setName("PVR Cinemas");
        theater1.setLocation("Forum Mall, Koramangala");
        theater1.setCity("Bangalore");
        theater1.setTotalScreens(5);
        theaterRepository.save(theater1);

        Theater theater2 = new Theater();
        theater2.setName("INOX");
        theater2.setLocation("Garuda Mall, Magrath Road");
        theater2.setCity("Bangalore");
        theater2.setTotalScreens(4);
        theaterRepository.save(theater2);

        // Create screens
        Screen screen1 = new Screen();
        screen1.setName("Screen 1");
        screen1.setTotalSeats(100);
        screen1.setTheater(theater1);
        screenRepository.save(screen1);

        Screen screen2 = new Screen();
        screen2.setName("Screen 2");
        screen2.setTotalSeats(80);
        screen2.setTheater(theater1);
        screenRepository.save(screen2);

        // Create seats
        createSeatsForScreen(screen1, 10, 10, 200.0, 300.0);
        createSeatsForScreen(screen2, 8, 10, 250.0, 350.0);

        // Create shows
        LocalDateTime now = LocalDateTime.now();

        Show show1 = new Show();
        show1.setMovie(movie1);
        show1.setScreen(screen1);
        show1.setShowTime(now.plusDays(1).withHour(18).withMinute(0));
        show1.setEndTime(show1.getShowTime().plus(movie1.getDuration()));
        showRepository.save(show1);

        Show show2 = new Show();
        show2.setMovie(movie2);
        show2.setScreen(screen2);
        show2.setShowTime(now.plusDays(1).withHour(21).withMinute(0));
        show2.setEndTime(show2.getShowTime().plus(movie2.getDuration()));
        showRepository.save(show2);

        // Create show seats
        createShowSeats(show1, screen1);
        createShowSeats(show2, screen2);

        // Create a sample user
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setPhoneNumber("9876543210");
        userRepository.save(user);

        System.out.println("Initial data loaded successfully");
    }

    private void createSeatsForScreen(Screen screen, int rows, int cols, double regularPrice, double premiumPrice) {
        char rowChar = 'A';
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                Seat seat = new Seat();
                seat.setSeatNumber(rowChar + String.valueOf(j));
                seat.setSeatType(i < rows / 2 ? SeatType.REGULAR : SeatType.PREMIUM);
                seat.setPrice(i < rows / 2 ? regularPrice : premiumPrice);
                seat.setScreen(screen);
                seatRepository.save(seat);
            }
            rowChar++;
        }
    }

    private void createShowSeats(Show show, Screen screen) {
        var seats = seatRepository.findByScreenId(screen.getId());
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setAvailable(true);
            showSeatRepository.save(showSeat);
        }
    }
}