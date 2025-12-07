package utils;

import java.util.Scanner;
import tictactoe.Board;
import tictactoe.Move;

/**
 * InputValidator.java
 * Ensures user inputs are valid (within range and empty cell).
 */
public class InputValidator {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Gets a valid move from user.
     * @param board The game board
     * @return a valid Move object
     */
    public static Move getValidMove(Board board) {
        int row, col;
        while (true) {
            System.out.print("Enter row (1-3): ");
            row = sc.nextInt() - 1;
            System.out.print("Enter col (1-3): ");
            col = sc.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                if (board.getGrid()[row][col] == ' ') {
                    return new Move(row, col);
                } else {
                    System.out.println("Cell already occupied. Try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter values between 1 and 3.");
            }
        }
    }
}
