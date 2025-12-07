🎮 Tic Tac Toe – Project Design Documentation

📌 Project Overview
This project is a console-based Tic Tac Toe game in Java, built using Object-Oriented Programming (OOP) principles. It allows two players (human vs human) to play on a 3x3 board. 

The game is designed to be modular, reusable, and extendable, ensuring good coding practices and professional quality.

🎯 Objectives
        Implement a clean, structured Java project using OOP concepts.

        Provide a user-friendly console experience with clear instructions and board visualization.

        Support multiple modes of play (2-player and optional AI).

        Ensure robust input validation and error handling.

        Maintain code readability, scalability, and testability.

📂 Project Structure

TicTacToe/
│── src/
│   └── tictactoe/-------
|                       ├── Main.java
│                       ├── Game.java
│                       ├── Board.java
│                       ├── Player.java
│                       ├── Move.java
│                       ├── AIPlayer.java
│                       └── utils/
│                           ├── InputValidator.java
│                           └── ConsolePrinter.java 
|                       
│                       
│     
│                    
│
│── docs/
│   └── design.md
│
│── README.md


🏗️ Class Design
1. Main.java
Entry point of the program.

Displays welcome message and game menu.

Starts the game loop (new game, show rules, exit).

2. Game.java
Controls the game flow: turn switching, move execution, win/draw checking.

Communicates with Board, Player, and AIPlayer.

Responsible for replay option and scoreboard tracking.

3. Board.java
Maintains the 3x3 grid as a 2D array.

Provides methods:

printBoard() → shows the current state.

placeMove(Move move, char symbol) → places a move if valid.

isCellEmpty() → checks if a move is valid.

checkWin() → determines if there is a winner.

isFull() → checks if the board is full (draw condition).

4. Player.java
Represents a player (human).

Stores name and symbol (X or O).

Provides method to take input for a move.

5. Move.java
Represents a move with row and col.

Keeps code cleaner when passing moves between classes.

6. InputValidator.java
Validates player inputs (numeric, within range, not already taken).

Ensures program doesn’t crash on wrong inputs.

7. ConsolePrinter.java
Handles all printing to the console.

Provides user-friendly board design and messages.

Keeps the UI separate from logic.

🔄 Game Flow
Game Start

Welcome screen, display rules, and ask for player mode.

Board Initialization

Create empty 3x3 board.

Player Setup

Assign symbols (X and O).

If AI mode → instantiate AIPlayer.

Gameplay Loop

Display board.

Current player makes a move.

Validate move → update board.

Check win/draw condition.

Switch turns.

Game End

Announce winner or draw.

Ask for replay or exit.

📊 Flowchart

          ┌───────────────┐
          │   Start Game  │
          └───────┬───────┘
                  │
          ┌───────▼────────┐
          │ Display Menu   │
          │ 1. Play Game   │
          │ 2. Rules       │
          │ 3. Exit        │
          └───────┬────────┘
                  │
          ┌───────▼────────┐
          │ Initialize     │
          │ Board & Players│
          └───────┬────────┘
                  │
        ┌─────────▼──────────┐
        │ Display Board       │
        │ Current Player Move │
        └─────────┬──────────┘
                  │
        ┌─────────▼──────────┐
        │ Validate & Place    │
        │ Move on Board       │
        └─────────┬──────────┘
                  │
       ┌──────────▼───────────┐
       │ Check Win Condition? │─Yes─► Announce Winner
       └──────────┬───────────┘
                  │
       ┌──────────▼───────────┐
       │ Check Draw Condition?│─Yes─► Announce Draw
       └──────────┬───────────┘
                  │
                  ▼
           Switch Player
                  │
                  └───────► Repeat Loop
🛠️ Features
✅ Human vs Human mode
✅ Replay option
✅ Scoreboard tracking
✅ Clear and attractive console display
✅ Robust input validation
✅ Unit testing for reliability

🔮 Future Enhancements
Extend to 4x4 or 5x5 Tic Tac Toe.

GUI version (Swing/JavaFX).

Multiplayer online mode.

Leaderboard with file/database storage.

📖 Rules of the Game
The game is played on a 3x3 grid.

Player 1 uses symbol X, Player 2 (or AI) uses symbol O.

Players take turns placing their symbol in an empty cell.

The first player to get 3 symbols in a row (horizontal, vertical, diagonal) wins.

If the board is full and no one has won → game ends in a draw.

✅ Conclusion
This project is a complete, well-structured, console-based game that demonstrates:

Java programming fundamentals

Object-Oriented Design (OOP)


