package utils;

import tictactoe.Board;
import tictactoe.Player;

/**
 * Handles all console-based printing for TicTacToe.
 */
public class ConsolePrinter {

    /**
     * Print welcome message when game starts.
     */
    public static void printWelcomeMessage() {
        System.out.println("=================================");
        System.out.println("      Welcome to TicTacToe!      ");
        System.out.println("=================================");
    }

    /**
     * Print the current game board.
     */
    public static void printBoard(Board board) {
        char[][] grid = board.getGrid();
        System.out.println();
        System.out.println("Current Board:");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(" ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
                if (j < grid[i].length - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < grid.length - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    /**
     * Print winner message.
     */
    public static void printWinner(Player player) {
        System.out.println("🎉 Congratulations " + player.getName() + "! You win with symbol '" + player.getSymbol() + "' 🎉");
    }

    /**
     * Print draw message.
     */
    public static void printDraw() {
        System.out.println("It's a draw! No one wins.");
    }
}
