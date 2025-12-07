# 📚 Library Management System

A **Java-based Library Management System** for managing books, members, and issued books.  
This project supports adding, searching, issuing, and returning books, along with maintaining transaction records.  

---

## 📑 Table of Contents

1. [Overview](#overview)  
2. [Features](#features)  
3. [Technologies Used](#technologies-used)  
4. [Project Structure](#project-structure)  
5. [Installation & Setup](#installation--setup)  
6. [Database Setup](#database-setup)  
7. [Usage Guide](#usage-guide)  
8. [Menu Options](#menu-options)  
9. [Enums & Status Codes](#enums--status-codes)  
10. [Contributing](#contributing)  
11. [License](#license)  

---

## 📌 Overview

The **Library Management System** is a console-based application developed in Java, designed for educational purposes to demonstrate **OOP concepts, DAO patterns, and database connectivity** using **MySQL**.  

It allows:
- Library staff to manage books
- Issue and return books
- Track available and issued copies

---

## ✨ Features

| Feature                         | Description |
|---------------------------------|-------------|
| **Add Books**                   | Insert new book records into the system |
| **View Books**                  | List all available books |
| **Search Books**                | Search by title, author, or ISBN |
| **Issue Books**                 | Issue books to members with a due date |
| **Return Books**                | Mark issued books as returned |
| **Book Status Tracking**        | Track whether a book is available or issued |
| **Persistent Storage**          | All data stored in MySQL database |
| **Enum-based Menu Options**     | Use enums for clean menu handling |

---

## 🛠 Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Core language |
| **MySQL** | Database |
| **JDBC** | Database connectivity |
| **Maven / Manual JAR** | Dependency management |
| **DAO Pattern** | Data access layer |
| **Enums** | Constants for menu options and statuses |

---

## 📂 Project Structure

```plaintext
LibraryManagementSystem/
│
├── src/
│   ├── MainApp.java
│   ├── dao/
│   │   ├── BookDAO.java
│   │   └── IssuedBookDAO.java
│   ├── model/
│   │   ├── Book.java
│   │   └── IssuedBook.java
│   ├── service/
│   │   ├── BookService.java
│   │   └── IssuedBookService.java
│   ├── util/
│   │   ├── DBUtil.java
│   │   └── DateUtil.java
│   └── enums/
│       ├── BookStatus.java
│       └── MenuOption.java
│
├── lib/
│   └── mysql-connector-j-9.4.0.jar
│
├── .gitignore
├── sources.txt
└── README.md
⚙ Installation & Setup
Clone the repository

git clone https://github.com/sure-trust/KHUSHI-KUMARI-g19-java.git
cd KHUSHI-KUMARI-g19-java/LibraryManagementSystem
Add MySQL Connector JAR (if not already in lib/)

lib/mysql-connector-j-9.4.0.jar
Compile the project

javac -cp ".;lib/mysql-connector-j-9.4.0.jar" src/**/*.java
Run the application

java -cp ".;lib/mysql-connector-j-9.4.0.jar;src" MainApp

Database setup: Create MySQL database.


CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    isbn VARCHAR(50) UNIQUE,
    status ENUM('AVAILABLE', 'ISSUED') DEFAULT 'AVAILABLE'
);

CREATE TABLE issued_books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    issued_to VARCHAR(255) NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id)
);
Update DB Credentials in DBUtil.java

private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

mysql> SHOW TABLES;
+-------------------------------------+
| Tables_in_library_management_system |
+-------------------------------------+
| books                               |
| issued_books                        |
+-------------------------------------+
2 rows in set (0.01 sec)

mysql> DESCRIBE books;
+----------+--------------+------+-----+-----------+----------------+
| Field    | Type         | Null | Key | Default   | Extra          |
+----------+--------------+------+-----+-----------+----------------+
| book_id  | int          | NO   | PRI | NULL      | auto_increment |
| title    | varchar(100) | NO   |     | NULL      |                |
| author   | varchar(100) | NO   |     | NULL      |                |
| quantity | int          | NO   |     | NULL      |                |
| status   | varchar(20)  | NO   | MUL | AVAILABLE |                |
+----------+--------------+------+-----+-----------+----------------+
5 rows in set (0.01 sec)

mysql> ^C
mysql> DESCRIBE issued_books;
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| issue_id     | int          | NO   | PRI | NULL    | auto_increment |
| book_id      | int          | NO   | MUL | NULL    |                |
| user_name    | varchar(100) | NO   |     | NULL    |                |
| issue_date   | datetime     | NO   |     | NULL    |                |
| return_date  | datetime     | YES  |     | NULL    |                |
| issue_status | varchar(20)  | YES  |     | ACTIVE  |                |
+--------------+--------------+------+-----+---------+----------------+
6 rows in set (0.00 sec)

mysql>
📖 Usage Guide
Once the app starts, you'll see a menu in the console.
Use the number keys to navigate.

📜 Menu Options
Option	Action
1	Add Book
2	View All Books
3	Search Book
4	Issue Book
5	Return Book
6	Exit

🏷 Enums & Status Codes
BookStatus.java
AVAILABLE, ISSUED
MenuOption.java
ADD_BOOK, VIEW_BOOKS, SEARCH_BOOK, ISSUE_BOOK, RETURN_BOOK, EXIT

OUTPUT:-
=== Library Management System ===
System started at: Aug 13, 2025 at 07:00 pm

Main Menu:
1. Add Book
2. View All Books
3. Issue Book to User
4. Return Book
5. Delete Book
6. Exit
Enter your choice: 2

--- All Books ---
ID: 1 | Harry Potter by J.K Rowling | Qty: 5 | Status: AVAILABLE
ID: 2 | Atomic Habits by James Clear | Qty: 6 | Status: AVAILABLE
ID: 3 | Principia by Isaac Netwon | Qty: 10 | Status: AVAILABLE
ID: 4 | Romeo and Juliet by William Shakespeare | Qty: 4 | Status: AVAILABLE
ID: 5 | Ramcharitra Manas by Tulsi Das | Qty: 5 | Status: AVAILABLE


📜 License
This project is licensed under the MIT License.

