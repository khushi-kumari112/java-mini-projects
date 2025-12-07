package model;

import java.time.LocalDateTime;

/**
 * Model class representing an IssuedBook entity.
 * Contains details about book issuance including user, issue date, and return date.
 */
public class IssuedBook {
    private int issueId;
    private int bookId;
    private String userName;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    
    /**
     * Constructs an IssuedBook with specified details.
     * @param issueId the unique identifier of the issue record
     * @param bookId the ID of the issued book
     * @param userName the name of the user who issued the book
     * @param issueDate the date when the book was issued
     * @param returnDate the date when the book was returned (can be null)
     */
    public IssuedBook(int issueId, int bookId, String userName, LocalDateTime issueDate, LocalDateTime returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.userName = userName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    
    // Getters and Setters
    public int getIssueId() {
        return issueId;
    }
    
    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public LocalDateTime getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }
    
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
    
    @Override
    public String toString() {
        return "Issue ID: " + issueId + 
               ", Book ID: " + bookId + 
               ", User: " + userName + 
               ", Issued on: " + issueDate + 
               (returnDate != null ? ", Returned on: " + returnDate : ", Not returned yet");
    }
}