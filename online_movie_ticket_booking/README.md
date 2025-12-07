 *Online Movie Ticket Booking System (Backend)*

 
A robust, RESTful backend service for a modern online movie ticket booking platform. This system handles core functionalities like movie catalog management, showtime scheduling, seat booking, and user order processing.

🚀 Features
Movie Management: CRUD operations for movies with details like genre, duration, cast, and director.

Theater & Screen Management: Manage theaters and their respective screens with seating capacity.

Showtime Scheduling: Create and manage showtimes for movies across different screens.

Seat Booking : Real-time seat availability checks and reservation system.

User & Order Management: User registration, authentication, and order history.

RESTful API: Clean, well-structured API endpoints following REST conventions.

Data Persistence: Persistent data storage using an in-memory mysql database (for development).

🛠️ Tech Stack

Framework: Spring Boot 

Language: Java 21+

Build Tool: Maven

Database: Mysql Database (In-memory for development)

Testing: Postman, Spring Boot Test

Validation: Jakarta Bean Validation

📦 Project Structure
src/
├── main/
│   ├── java/com/online_movie_booking/
│   │   ├── controller/       # REST API Controllers
│   │   ├── service/          # Business logic layer
│   │   ├── repository/       # Data access layer (JPA Repositories)
│   │   ├── model/            # Entity classes (JPA Models)
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── exception/        # Custom exception handling
│   │   └── config/           # Application configuration
│   └── resources/
│       ├── application.properties # Main configuration
│       └── data.sql          # Initial sample data
└── test/                     # Unit and integration tests

🗃️ Core Data Models (Entities)
The system is built around several key entities:

Movie: Stores movie metadata.

Theater: Represents a cinema location.

Screen: A hall within a theater with a specific seating layout.

Showtime: A specific screening of a movie in a screen at a given time.

Seat: A physical seat within a screen.

User: An end-user of the application.

Booking: A user's booking for a showtime, containing one or more seats.

🔌 API Endpoints Overview

  HTTP Method  	Endpoint	 Description
  GET	/api/movies       	Retrieve all movies
  GET	/api/movies/{id}	  Get a specific movie by ID
  POST	/api/movies	      Create a new movie (Admin)
  PUT	/api/movies/{id}	  Update a movie (Admin)
  DELETE	/api/movies/{id}	Delete a movie (Admin)
  GET	/api/theaters      	Get all theaters
  GET	/api/showtimes?movieId={}&theaterId={}&date={}	Find showtimes based on filters
  GET	/api/showtimes/{showtimeId}/seats	 Get seat availability for a show
  POST	/api/bookings	      Create a new booking
  GET	/api/users/{userId}/bookings	  Get booking history for a user

  📄 License
This project is licensed for educational and portfolio purposes.
