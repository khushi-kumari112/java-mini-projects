package model;

import enums.BookStatus;

/**
 * Represents a book in the library system with all relevant attributes
 * and status tracking capabilities.
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private int quantity;
    private BookStatus status;

    public Book(int bookId, String title, String author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        setQuantity(quantity); // Uses setter to maintain status consistency
    }

    public Book() {
        this.status = BookStatus.AVAILABLE;
    }

    // Getters
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getQuantity() { return quantity; }
    public BookStatus getStatus() { return status; }

    // Setters with validation
    public void setBookId(int bookId) { 
        this.bookId = bookId; 
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = author;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
        updateStatusBasedOnQuantity();
    }

    public void setStatus(BookStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    private void updateStatusBasedOnQuantity() {
        this.status = (quantity > 0) ? BookStatus.AVAILABLE : BookStatus.ISSUED;
    }

    @Override
    public String toString() {
        return String.format(
            "Book[ID: %d, Title: %s, Author: %s, Qty: %d, Status: %s]",
            bookId, title, author, quantity, status
        );
    }
}