package dao;

import model.IssuedBook;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for IssuedBook entity.
 * Handles all database operations related to book issuance.
 */
public class IssuedBookDAO {
    private static final String ISSUE_BOOK_SQL =
        "INSERT INTO issued_books (book_id, user_name, issue_date) VALUES (?, ?, ?)";
    private static final String RETURN_BOOK_SQL =
        "UPDATE issued_books SET return_date = ? WHERE issue_id = ? AND return_date IS NULL";
    private static final String GET_ACTIVE_ISSUES_SQL =
        "SELECT * FROM issued_books WHERE return_date IS NULL";
    private static final String GET_ALL_ISSUES_SQL =
        "SELECT * FROM issued_books";
    private static final String GET_ISSUE_BY_ID_SQL =
        "SELECT * FROM issued_books WHERE issue_id = ?";

    /**
     * Issues a book to a user with the current timestamp.
     *
     * @param bookId   the ID of the book being issued
     * @param userName the name of the user
     */
    public boolean issueBook(int bookId, String userName) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ISSUE_BOOK_SQL)) {

            stmt.setInt(1, bookId);
            stmt.setString(2, userName);
            stmt.setString(3, DateUtil.getCurrentDateTime()); // Auto-sets current DB-friendly date

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Marks a book as returned with the current timestamp.
     *
     * @param issueId the ID of the issued book record
     */
    public boolean returnBook(int issueId) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(RETURN_BOOK_SQL)) {

            stmt.setString(1, DateUtil.getCurrentDateTime()); // Auto-sets current DB-friendly date
            stmt.setInt(2, issueId);

            return stmt.executeUpdate() > 0;
        }
    }

    public List<IssuedBook> getActiveIssuedBooks() throws SQLException {
        List<IssuedBook> issuedBooks = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ACTIVE_ISSUES_SQL)) {

            while (rs.next()) {
                issuedBooks.add(mapResultSetToIssuedBook(rs));
            }
        }
        return issuedBooks;
    }

    public List<IssuedBook> getAllIssuedBooks() throws SQLException {
        List<IssuedBook> issuedBooks = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_ISSUES_SQL)) {

            while (rs.next()) {
                issuedBooks.add(mapResultSetToIssuedBook(rs));
            }
        }
        return issuedBooks;
    }

    public IssuedBook getIssuedBookById(int issueId) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ISSUE_BY_ID_SQL)) {

            stmt.setInt(1, issueId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToIssuedBook(rs) : null;
            }
        }
    }

    private IssuedBook mapResultSetToIssuedBook(ResultSet rs) throws SQLException {
        return new IssuedBook(
            rs.getInt("issue_id"),
            rs.getInt("book_id"),
            rs.getString("user_name"),
            DateUtil.parseFromDB(rs.getString("issue_date")),
            rs.getString("return_date") != null ?
                DateUtil.parseFromDB(rs.getString("return_date")) : null
        );
    }
}
