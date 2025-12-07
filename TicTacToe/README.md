# Tic Tac Toe Game (Java)

Welcome to the **Tic Tac Toe** Java console game! 🎮

This is a simple **two-player Tic Tac Toe game** built using Java.  
Players take turns to mark their symbol (X or O) on a 3x3 board until someone wins or the game ends in a draw.

---

## **Features**
- Console-based game with interactive input
- Two-player mode
- Displays the current board after each move
- Checks for winning condition or draw
- Clean, modular Java code with packages:
  - `tictactoe` → main game classes  
  - `utils` → helper classes for validation, input, etc.

---

## **How to Run**

1. **Clone the repository**
   ```bash
   git clone https://github.com/sure-trust/KHUSHI-KUMARI-g19-java.git
Navigate to the TicTacToe folder
 cd "Mini projects/TicTacToe/src"
Compile all Java files
 javac tictactoe/*.java utils/*.java
Run the game
 java tictactoe.Main
Sample Output

===== Welcome to Tic Tac Toe =====
Enter name for Player 1 (X): Khushi #name of player 1
Enter name for Player 2 (O): Dhirr  #name of player 2
=================================
      Welcome to TicTacToe!      
=================================

Current Board:
   |   |  
---+---+---
   |   |  
---+---+---
   |   |  

Enter row (1-3): 1
Enter col (1-3): 1

Current Board:
 X |   |  
---+---+---
   |   |
---+---+---
   |   |

... (game continues)

? Congratulations Dhirr! You win with symbol 'O' ?
Project Structure

TicTacToe/
├── src/
│   ├── tictactoe/       # main game classes
│   │   ├── Main.java
│   │   ├── Board.java
│   │   ├── Game.java
|   |   |__Move.java
│   │   └── Player.java
│   └── utils/           # helper classes
│       └── InputValidator.java
|       |__ConsolePrinter.java
└── README.md
Technologies Used:-
    Java (JDK 8+)
    
    Console I/O
    
    Modular programming with packages

Author
Khushi Kumari
GitHub: khushi-kumari112





