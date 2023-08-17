import java.util.Scanner;

public class TicTacToeGame {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Current Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon) {
            System.out.println("Player " + currentPlayer + ", enter row (0-2) and column (0-2) separated by space:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;
            displayBoard();

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                gameWon = true;
            } else if (checkDraw()) {
                System.out.println("It's a draw!");
                gameWon = true;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer;
    }

    private static boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false; // There is an empty cell, game is not a draw
                }
            }
        }
        return true; // All cells are filled, game is a draw
    }
}
