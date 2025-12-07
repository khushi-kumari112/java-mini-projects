package dao;

import model.Book;
import enums.BookStatus;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all database operations for Book entities with proper
 * transaction management and error handling.
 */
public class BookDAO {
    private static final String ADD_BOOK_SQL = 
        "INSERT INTO books (title, author, quantity, status) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_BOOKS_SQL = "SELECT * FROM books";
    private static final String GET_BOOK_BY_ID_SQL = 
        "SELECT * FROM books WHERE book_id = ?";
    private static final String UPDATE_BOOK_SQL = 
        "UPDATE books SET title = ?, author = ?, quantity = ?, status = ? WHERE book_id = ?";
    private static final String UPDATE_STATUS_SQL = 
        "UPDATE books SET status = ? WHERE book_id = ?";
    private static final String DELETE_BOOK_SQL = 
        "DELETE FROM books WHERE book_id = ?";

    public boolean addBook(Book book) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ADD_BOOK_SQL)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getQuantity());
            stmt.setString(4, book.getStatus().name());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_BOOKS_SQL)) {
            
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    public Book getBookById(int bookId) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_BOOK_BY_ID_SQL)) {
            
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToBook(rs) : null;
            }
        }
    }

    public boolean updateBook(Book book) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK_SQL)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getQuantity());
            stmt.setString(4, book.getStatus().name());
            stmt.setInt(5, book.getBookId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateBookStatus(int bookId, BookStatus status) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_STATUS_SQL)) {
            
            stmt.setString(1, status.name());
            stmt.setInt(2, bookId);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int bookId) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BOOK_SQL)) {
            
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book(
            rs.getInt("book_id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getInt("quantity")
        );
        book.setStatus(BookStatus.valueOf(rs.getString("status")));
        return book;
    }
}
