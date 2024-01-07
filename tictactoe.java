import java.util.Scanner;
public class tictactoe {
    public static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard();

        while (true) {
            playerMove('X', scanner);
            if (isGameFinished()) break;

            aiMove();
            if (isGameFinished()) break;
        }

        scanner.close();
    }
    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
        System.out.println();
    }

    private static void playerMove(char playerSymbol, Scanner scanner) {
        int row, col;
        do {
            System.out.print("Enter your move (row [1-3] and column [1-3] separated by space): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));

        board[row][col] = playerSymbol;
        printBoard();
    }

    private static void aiMove() {
        int[] bestMove = minimax(0, 'O');
        board[bestMove[0]][bestMove[1]] = 'O';
        System.out.println("AI plays at: " + (bestMove[0] + 1) + " " + (bestMove[1] + 1));
        printBoard();
    }

    private static int[] minimax(int depth, char currentPlayer) {
        int[] bestMove = {-1, -1};
        int bestScore = (currentPlayer == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currentPlayer;
                    int score = minimaxHelper(depth + 1, currentPlayer == 'O' ? 'X' : 'O');
                    board[i][j] = ' ';

                    if ((currentPlayer == 'O' && score > bestScore) || (currentPlayer == 'X' && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimaxHelper(int depth, char currentPlayer) {
        if (hasPlayerWon('O')) return 1;
        if (hasPlayerWon('X')) return -1;
        if (isBoardFull()) return 0;

        int bestScore = (currentPlayer == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currentPlayer;
                    int score = minimaxHelper(depth + 1, currentPlayer == 'O' ? 'X' : 'O');
                    board[i][j] = ' ';

                    if (currentPlayer == 'O') {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        return true;
    }

    private static boolean hasPlayerWon(char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol) ||
                    (board[0][i] == playerSymbol && board[1][i] == playerSymbol && board[2][i] == playerSymbol)) {
                return true;
            }
        }
        return (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) ||
                (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("It's a draw!");
        return true;
    }

    private static boolean isGameFinished() {
        if (hasPlayerWon('X')) {
            System.out.println("Congratulations! You won!");
            return true;
        } else if (hasPlayerWon('O')) {
            System.out.println("AI wins! Better luck next time.");
            return true;
        } else if (isBoardFull()) {
            System.out.println("The game is a draw!");
            return true;
        }
        return false;
    }
}

