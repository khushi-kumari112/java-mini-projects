package tictactoe;

import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Tic Tac Toe game.
 * Handles player setup and starts the game.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=====  Welcome to Tic Tac Toe  =====");
        System.out.print("Enter name for Player 1 (X): ");
        String name1 = sc.nextLine();
        Player player1 = new Player(name1, 'X');

        System.out.print("Enter name for Player 2 (O): ");
        String name2 = sc.nextLine();
        Player player2 = new Player(name2, 'O');

        Game game = new Game(player1, player2);
        game.start();

        sc.close();
    }
}
