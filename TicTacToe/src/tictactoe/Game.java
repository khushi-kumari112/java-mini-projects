package tictactoe;

import utils.InputValidator;
import utils.ConsolePrinter;

/**
 * Game.java
 * Manages the flow of a TicTacToe game.
 */
public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Player 1 starts
    }

    /**
     * Starts the main game loop.
     */
    public void start() {
        ConsolePrinter.printWelcomeMessage();

        while (true) {
            ConsolePrinter.printBoard(board);

            // Get a valid move
            Move move = InputValidator.getValidMove(board);

            // Make the move
            board.placeMove(move, currentPlayer.getSymbol());

            // Check for win
            if (board.checkWin(currentPlayer.getSymbol())) {
                ConsolePrinter.printBoard(board);
                ConsolePrinter.printWinner(currentPlayer);
                break;
            }

            // Check for draw
            if (board.isFull()) {
                ConsolePrinter.printBoard(board);
                ConsolePrinter.printDraw();
                break;
            }

            // Switch players
            switchPlayer();
        }
    }

    /**
     * Switch current player after each turn.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
