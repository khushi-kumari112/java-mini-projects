
    // CalculatorApp.java
// This class handles user interaction, input/output, and menu navigation.
// It uses the Calculator class to perform arithmetic operations.

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        // Create an object of Calculator to perform operations
        Calculator calculator = new Calculator();

        // Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Variable to store user's menu choice
        int choice;

        // Menu-driven loop continues until user selects Exit (option 7)
        do {
            // Displaying the menu
            System.out.println("\n===== Java Calculator Menu =====");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Show Result");
            System.out.println("6. Reset Calculator");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            try {
                // Taking user's choice input
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Addition operation
                        System.out.print("Enter number to add: ");
                        double addNum = getValidNumber(scanner);
                        calculator.add(addNum);
                        System.out.println("Number added successfully.");
                        break;

                    case 2:
                        // Subtraction operation
                        System.out.print("Enter number to subtract: ");
                        double subNum = getValidNumber(scanner);
                        calculator.subtract(subNum);
                        System.out.println("Number subtracted successfully.");
                        break;

                    case 3:
                        // Multiplication operation
                        System.out.print("Enter number to multiply: ");
                        double mulNum = getValidNumber(scanner);
                        calculator.multiply(mulNum);
                        System.out.println("Number multiplied successfully.");
                        break;

                    case 4:
                        // Division operation with exception handling for division by zero
                        System.out.print("Enter number to divide: ");
                        double divNum = getValidNumber(scanner);
                        try {
                            calculator.divide(divNum);
                            System.out.println("Number divided successfully.");
                        } catch (ArithmeticException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        // Displaying the current result
                        System.out.println("Current Result: " + calculator.getResult());
                        break;

                    case 6:
                        // Resetting the calculator
                        calculator.reset();
                        System.out.println("Calculator has been reset.");
                        break;

                    case 7:
                        // Exiting the program
                        System.out.println("Exiting the Calculator... Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select a valid option (1-7).");
                }

            } catch (InputMismatchException e) {
                // Handling invalid non-integer menu inputs
                System.out.println("Invalid input! Please enter numeric values only.");
                scanner.next(); // Clear the invalid input from the scanner buffer
                choice = 0; // Reset choice to continue loop
            }

        } while (choice != 7);

        // Close the scanner resource
        scanner.close();
    }

    /**
     * Utility method to ensure user enters a valid double number.
     * It keeps asking the user until a valid number is entered.
     *
     * @param scanner The Scanner object for input
     * @return The valid double number entered by the user
     */
    public static double getValidNumber(Scanner scanner) {
        double num;
        while (true) {
            try {
                num = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
        return num;
    }
}

