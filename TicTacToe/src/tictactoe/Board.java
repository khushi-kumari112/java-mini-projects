package tictactoe;

import utils.ConsolePrinter;

/**
 * Board.java
 * Represents the Tic Tac Toe game board.
 * Maintains the 3x3 grid and provides methods for move placement,
 * board display, and win/draw checking.
 */
public class Board {
    private char[][] grid; // 3x3 board
    private final int SIZE = 3;

    /**
     * Constructor initializes an empty board.
     */
    public Board() {
        grid = new char[SIZE][SIZE];
        initializeBoard();
    }

    /**
     * Initializes the board with empty cells (' ').
     */
    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    /**
     * Places a move on the board.
     * @param move the move (row, col)
     * @param symbol the player's symbol ('X' or 'O')
     * @return true if move is successful, false if cell already occupied
     */
    public boolean placeMove(Move move, char symbol) {
        if (grid[move.getRow()][move.getCol()] == ' ') {
            grid[move.getRow()][move.getCol()] = symbol;
            return true;
        }
        return false;
    }

    /**
     * Displays the board using ConsolePrinter.
     */
    public void printBoard() {
        ConsolePrinter.printBoard(this);
    }

    /**
     * Checks if a player has won.
     * @param symbol the symbol to check
     * @return true if the player has won
     */
    public boolean checkWin(char symbol) {
        // Rows & Columns
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }
        // Diagonals
        if ((grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
            (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the board is full (draw condition).
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == ' ') return false;
            }
        }
        return true;
    }

    /**
     * Getter for the board grid (used by InputValidator).
     * @return 2D array representing the current board
     */
    public char[][] getGrid() {
        return grid;
    }
}
