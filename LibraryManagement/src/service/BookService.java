package service;

import dao.BookDAO;
import model.Book;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for Book operations with enhanced error handling
 * and integration with updated components.
 */
public class BookService {
    private final BookDAO bookDAO;
    private final Scanner scanner;
    
    public BookService(BookDAO bookDAO, Scanner scanner) {
        this.bookDAO = bookDAO;
        this.scanner = scanner;
    }
    
    public void addBook() {
        System.out.println("\n--- Add New Book ---");
        
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter author name: ");
            String author = scanner.nextLine();
            
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            Book book = new Book(0, title, author, quantity);
            boolean success = bookDAO.addBook(book);
            
            System.out.println(success ? "Book added successfully!" : "Failed to add book.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void viewAllBooks() {
        System.out.println("\n--- All Books ---");
        try {
            List<Book> books = bookDAO.getAllBooks();
            if (books.isEmpty()) {
                System.out.println("No books found.");
            } else {
                books.forEach(book -> 
                    System.out.printf("ID: %d | %s by %s | Qty: %d | Status: %s%n",
                        book.getBookId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getQuantity(),
                        book.getStatus())
                );
            }
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
    
    public void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        try {
            System.out.print("Enter book ID to delete: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            
            boolean success = bookDAO.deleteBook(bookId);
            System.out.println(success ? "Book deleted successfully!" 
                : "Book not found or could not be deleted.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid book ID. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public Book getBookById(int bookId) {
        try {
            return bookDAO.getBookById(bookId);
        } catch (Exception e) {
            System.out.println("Error retrieving book: " + e.getMessage());
            return null;
        }
    }
    
    public boolean updateBook(Book book) {
        try {
            return bookDAO.updateBook(book);
        } catch (Exception e) {
            System.out.println("Error updating book: " + e.getMessage());
            return false;
        }
    }
}