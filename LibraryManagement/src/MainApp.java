import dao.BookDAO;
import dao.IssuedBookDAO;
import enums.MenuOption;
import service.BookService;
import service.IssuedBookService;
import util.DBUtil;
import util.DateUtil;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The main controller class for the Library Management System
 * that orchestrates all operations and user interactions.
 */
public class MainApp {
    private final BookService bookService;
    private final IssuedBookService issuedBookService;
    private final Scanner scanner;

    public MainApp(BookService bookService, IssuedBookService issuedBookService, Scanner scanner) {
        this.bookService = bookService;
        this.issuedBookService = issuedBookService;
        this.scanner = scanner;
    }

    public void run() {
        displayWelcomeMessage();
        boolean running = true;

        while (running) {
            printMenu();
            running = processUserChoice(getValidUserChoice());
        }

        shutdownApplication();
    }

    private void displayWelcomeMessage() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("System started at: " + DateUtil.formatForDisplay(LocalDateTime.now()));
    }

    private void printMenu() {
        System.out.println("\nMain Menu:");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d. %s%n", option.getValue(), option.getDisplayText());
        }
        System.out.print("Enter your choice: ");
    }

    private int getValidUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Indicates invalid input
        }
    }

    private boolean processUserChoice(int choice) {
        try {
            MenuOption option = MenuOption.fromValue(choice);

            switch (option) {
                case ADD_BOOK -> bookService.addBook();
                case VIEW_BOOKS -> bookService.viewAllBooks();
                case ISSUE_BOOK -> processIssueBook();
                case RETURN_BOOK -> processReturnBook();
                case DELETE_BOOK -> bookService.deleteBook();
                case EXIT -> {
                    return false;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private void processIssueBook() {
        System.out.println("\nCurrent time: " + DateUtil.formatForDisplay(LocalDateTime.now()));
        issuedBookService.issueBook();
    }

    private void processReturnBook() {
        System.out.println("\nCurrent time: " + DateUtil.formatForDisplay(LocalDateTime.now()));
        issuedBookService.returnBook();
    }

    private void shutdownApplication() {
        System.out.println("\nSystem shutting down at: " +
                DateUtil.formatForDisplay(LocalDateTime.now()));
        System.out.println("Thank you for using the Library Management System!");
        DBUtil.closeConnection();
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            DBUtil.getConnection();
            BookDAO bookDAO = new BookDAO();
            IssuedBookDAO issuedBookDAO = new IssuedBookDAO();

            Scanner scanner = new Scanner(System.in);
            BookService bookService = new BookService(bookDAO, scanner);
            IssuedBookService issuedBookService = new IssuedBookService(issuedBookDAO, bookDAO, scanner);

            new MainApp(bookService, issuedBookService, scanner).run();
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
