# 🚀 Java Mini Projects Collection

This Java Mini Projects Package includes four real-world console/GUI applications built using Core Java and Spring Boot. It enhances problem-solving and logic building while applying concepts like classes, inheritance, file handling, exception handling, collections, and JDBC-based database connectivity.

---

## 📦 Projects Included

### 1️⃣ 📚 Library Management System
A console-based application to manage library operations digitally.

**Features:**
- Add, update, delete, and search books
- Issue and return books with due date tracking
- Maintain student/member records
- Fine calculation for late returns
- Real-time availability status
- Search books by title/author

**Tech Stack:** Core Java, OOP, Collections Framework, File Handling, JDBC

**How to Run:**
```bash
cd library-management
javac LibraryManagement.java
java LibraryManagement
```

---

### 2️⃣ ⭕ Tic-Tac-Toe Game
A simple two-player console-based game with an interactive interface.

**Features:**
- 2-player mode (Player X vs Player O)
- Real-time input validation
- Winner detection and draw handling
- Restart option for continuous gameplay
- Clean, user-friendly console interface

**Tech Stack:** Core Java, OOP Concepts

**How to Run:**
```bash
cd tic-tac-toe
javac TicTacToe.java
java TicTacToe
```

---

### 3️⃣ 🎬 Online Movie Ticket Booking System
A backend-based web application built with Spring Boot for booking movie tickets.

**Features:**
- User and admin authentication system
- Movie and show management (CRUD operations)
- Interactive seat selection and booking
- Booking history and cancellation
- RESTful APIs for seamless integration
- Database persistence with JPA/Hibernate
- Booking confirmation with unique booking ID

**Tech Stack:** Java, Spring Boot, Spring MVC, REST API, MySQL, JPA/Hibernate, Spring Data JPA

**How to Run:**
```bash
cd movie-ticket-booking
mvn clean install
mvn spring-boot:run
```
Access the application at `http://localhost:8080`

**Default Admin Credentials:**
- Username: `admin`
- Password: `admin123`

**API Endpoints:**
- `GET /api/movies` - Get all movies
- `POST /api/bookings` - Create new booking
- `GET /api/bookings/{id}` - Get booking details
- `DELETE /api/bookings/{id}` - Cancel booking

---

### 4️⃣ 🔢 Command Line Calculator
A Java-based CLI calculator for basic and advanced arithmetic operations.

**Features:**
- Basic operations: Addition, subtraction, multiplication, division
- Advanced operations: Power, square root, percentage
- Continuous calculation option
- Exception handling for invalid inputs
- Memory functions (Store, Recall, Clear)
- Clean error messages and user guidance

**Tech Stack:** Core Java, Exception Handling, Mathematical Libraries

**How to Run:**
```bash
cd command-line-calculator
javac Calculator.java
java Calculator
```

**Usage Examples:**
```
Enter operation: 25 + 75
Result: 100.0

Enter operation: 12 * 8
Result: 96.0

Enter operation: sqrt(144)
Result: 12.0
```

---

## 🛠️ Technologies Used

| Technology | Projects Used In |
|------------|------------------|
| Core Java | All Projects |
| OOP Concepts | All Projects |
| Spring Boot | Movie Ticket Booking |
| Collections Framework | Library Management |
| File Handling | Library Management, Calculator |
| JDBC / MySQL | Library Management, Movie Booking |
| REST APIs | Movie Ticket Booking |
| JPA/Hibernate | Movie Ticket Booking |
| Exception Handling | All Projects |
| Maven | Movie Ticket Booking |

---

## 🎯 Learning Outcomes

These projects demonstrate practical implementation of:

- ✅ Strong understanding of Core Java & OOP principles
- ✅ Hands-on Spring Boot and REST API development experience
- ✅ Database integration using JDBC and JPA
- ✅ Real-world application logic and business requirements
- ✅ Improved problem-solving and debugging skills
- ✅ Software Development Life Cycle (SDLC) practices
- ✅ Exception handling and input validation
- ✅ MVC architecture and design patterns

---

## 📋 Prerequisites

### For All Projects:
- Java Development Kit (JDK) 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or text editor
- Git for version control

### For Spring Boot Projects:
- Maven (for dependency management)
- MySQL/PostgreSQL (or use embedded H2 database)
- Postman (for testing REST APIs)

---

## ▶️ Installation & Setup

### 1. Clone the Repository:
```bash
git clone https://github.com/khushi-kumari112/java-mini-projects.git
cd java-mini-projects
```

### 2. For Core Java Projects (Library Management, Tic-Tac-Toe, Calculator):
```bash
cd project-folder
javac *.java
java MainClass
```

### 3. For Spring Boot Project (Movie Ticket Booking):

**Configure Database:**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**Build and Run:**
```bash
cd movie-ticket-booking
mvn clean install
mvn spring-boot:run
```

---

## 📁 Project Structure

```
java-mini-projects/
│
├── library-management/
│   ├── src/
│   ├── LibraryManagement.java
│   └── README.md
│
├── tic-tac-toe/
│   ├── src/
│   ├── TicTacToe.java
│   └── README.md
│
├── movie-ticket-booking/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
│
├── command-line-calculator/
│   ├── src/
│   ├── Calculator.java
│   └── README.md
│
└── README.md
```

---

## 🚀 Future Enhancements

### Library Management System:
- GUI using JavaFX or Swing
- Email notifications for due dates
- Book reservation system
- Admin dashboard with analytics

### Tic-Tac-Toe:
- AI opponent with multiple difficulty levels
- Graphical user interface (GUI)
- Network multiplayer mode
- Score tracking and leaderboard

### Movie Ticket Booking:
- Payment gateway integration (Razorpay/Stripe)
- QR code ticket generation
- User reviews and ratings system
- Email confirmation and tickets
- Push notifications for new releases

### Command Line Calculator:
- Scientific calculator mode
- Unit conversions
- Currency conversion with live rates
- Graphical calculator interface
- Calculation history with export feature

---


## 📝 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👩‍💻 Author

**Khushi Kumari**  
Java Developer | Spring Boot | Full Stack Enthusiast


## 🙏 Acknowledgments

- Thanks to **Sure Trust** for the learning opportunity and guidance
- Inspired by real-world applications and industry best practices
- Built as part of Java development learning journey
- Special thanks to the open-source community for valuable resources


---

## 📊 Project Status

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

**⭐ If you find these projects helpful, please consider giving this repository a star! ⭐**

---

**Happy Coding! 💻✨**
