package service;

import dao.BookDAO;
import dao.IssuedBookDAO;
import model.Book;
import model.IssuedBook;
import util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for managing book issuance and returns.
 * Handles business logic and user interaction for book circulation.
 */
public class IssuedBookService {
    private final IssuedBookDAO issuedBookDAO;
    private final BookDAO bookDAO;
    private final Scanner scanner;
    
    public IssuedBookService(IssuedBookDAO issuedBookDAO, BookDAO bookDAO, Scanner scanner) {
        this.issuedBookDAO = issuedBookDAO;
        this.bookDAO = bookDAO;
        this.scanner = scanner;
    }
    
    /**
     * Handles the book issuing process including validation and status updates.
     */
    public void issueBook() {
        System.out.println("\n--- Issue Book ---");
        try {
            System.out.print("Enter book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            
            Book book = bookDAO.getBookById(bookId);
            if (book == null) {
                System.out.println("Book not found!");
                return;
            }
            
            if (book.getQuantity() <= 0) {
                System.out.println("No available copies!");
                return;
            }
            
            System.out.print("Enter user name: ");
            String userName = scanner.nextLine();
            
            boolean success = issuedBookDAO.issueBook(bookId, userName);
            
            if (success) {
                book.setQuantity(book.getQuantity() - 1);
                bookDAO.updateBook(book);
                System.out.println("Book issued successfully at: " + 
                    DateUtil.formatForDisplay(LocalDateTime.now()));
            } else {
                System.out.println("Failed to issue book.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }
    
    /**
     * Handles the book return process including validation and status updates.
     */
    public void returnBook() {
        System.out.println("\n--- Return Book ---");
        try {
            System.out.print("Enter issue ID: ");
            int issueId = Integer.parseInt(scanner.nextLine());
            
            IssuedBook issuedBook = issuedBookDAO.getIssuedBookById(issueId);
            if (issuedBook == null) {
                System.out.println("Issue record not found!");
                return;
            }
            
            if (issuedBook.getReturnDate() != null) {
                System.out.println("This book was already returned on: " + 
                    DateUtil.formatForDisplay(issuedBook.getReturnDate()));
                return;
            }
            
            boolean success = issuedBookDAO.returnBook(issueId);
            
            if (success) {
                Book book = bookDAO.getBookById(issuedBook.getBookId());
                book.setQuantity(book.getQuantity() + 1);
                bookDAO.updateBook(book);
                System.out.println("Book returned successfully at: " + 
                    DateUtil.formatForDisplay(LocalDateTime.now()));
            } else {
                System.out.println("Failed to return book.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
    
    /**
     * Displays all currently issued books that haven't been returned.
     */
    public void viewActiveIssuedBooks() {
        System.out.println("\n--- Active Issued Books ---");
        try {
            List<IssuedBook> issuedBooks = issuedBookDAO.getActiveIssuedBooks();
            if (issuedBooks.isEmpty()) {
                System.out.println("No active issued books.");
            } else {
                issuedBooks.forEach(ib -> 
                    System.out.printf("Issue ID: %d | Book ID: %d | User: %s | Issued: %s%n",
                        ib.getIssueId(),
                        ib.getBookId(),
                        ib.getUserName(),
                        DateUtil.formatForDisplay(ib.getIssueDate()))
                );
            }
        } catch (Exception e) {
            System.out.println("Error loading issued books: " + e.getMessage());
        }
    }
    
    /**
     * Displays complete history of all book issuances.
     */
    public void viewAllIssuedBooks() {
        System.out.println("\n--- All Issued Books History ---");
        try {
            List<IssuedBook> issuedBooks = issuedBookDAO.getAllIssuedBooks();
            if (issuedBooks.isEmpty()) {
                System.out.println("No issuance history found.");
            } else {
                issuedBooks.forEach(ib -> {
                    String returnStatus = (ib.getReturnDate() != null) ?
                        "Returned: " + DateUtil.formatForDisplay(ib.getReturnDate()) :
                        "Not returned yet";
                    System.out.printf("ID: %d | Book: %d | User: %s | Issued: %s | %s%n",
                        ib.getIssueId(),
                        ib.getBookId(),
                        ib.getUserName(),
                        DateUtil.formatForDisplay(ib.getIssueDate()),
                        returnStatus);
                });
            }
        } catch (Exception e) {
            System.out.println("Error loading history: " + e.getMessage());
        }
    }
}
