// Calculator.java
// This class handles all the core arithmetic logic for the calculator
// Follows OOP principles: Encapsulation and Abstraction

public class Calculator {

    // Private field to store the current result
    private double result;

    // Constructor: Initializes the calculator with result = 0
    public Calculator() {
        result = 0.0;
    }

    /**
     * Adds a number to the current result.
     * @param num The number to add.
     */
    public void add(double num) {
        result += num;
    }

    /**
     * Subtracts a number from the current result.
     * @param num The number to subtract.
     */
    public void subtract(double num) {
        result -= num;
    }

    /**
     * Multiplies the current result by the given number.
     * @param num The number to multiply with.
     */
    public void multiply(double num) {
        result *= num;
    }

    /**
     * Divides the current result by the given number.
     * Handles division by zero by throwing an ArithmeticException.
     * @param num The divisor.
     * @throws ArithmeticException if division by zero is attempted.
     */
    public void divide(double num) throws ArithmeticException {
        if (num == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        result /= num;
    }

    /**
     * Returns the current result of the calculator.
     * @return The current result as double.
     */
    public double getResult() {
        return result;
    }

    /**
     * Resets the calculator result back to zero.
     */
    public void reset() {
        result = 0.0;
    }
}
