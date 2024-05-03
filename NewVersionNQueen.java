import java.util.Arrays;

public class NQueens {
    // This method solves the N-Queens problem by initializing the board and calling the recursive solveNQueens method
    public static void solveNQueens(int n) {
        char[][] board = new char[n][n]; // Create a n x n board
        for (char[] row : board) Arrays.fill(row, '_'); // Fill the board with '_' (empty cells)
        if (solveNQueens(board, 0)) { // Call the recursive method with the board and starting column index 0
            printBoard(board); // Print the board if a solution is found
        } else {
            System.out.println("No solution exists."); // Print a message if no solution is found
        }
    }

    // This recursive method places queens on the board by trying all possible positions in each column
    private static boolean solveNQueens(char[][] board, int col) {
        if (col == board.length) { // If all columns are filled
            return true; // Return true (a solution is found)
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) { // If it's safe to place a queen at (row, col)
                board[row][col] = 'Q'; // Place the queen
                if (solveNQueens(board, col + 1)) { // Recursively try placing queens in the next column
                    return true; // Return true if a solution is found
                }
                board[row][col] = '_'; // Backtrack (remove the queen)
            }
        }

        return false; // If no solution is found in the current column, return false
    }

    // This method checks if it's safe to place a queen at the given position
    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;
        // Check the left side of the current position
        for (int i = 0; i < col; i++) if (board[row][i] == 'Q') return false;
        // Check the upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 'Q') return false;
        // Check the lower-left diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) if (board[i][j] == 'Q') return false;
        return true; // If no conflicts were found, it's safe to place a queen
    }

    // This method prints the board
    private static void printBoard(char[][] board) {
        for (char[] row : board) { // For each row in the board
            for (char cell : row) { // For each cell in the row
                System.out.print(cell + " "); // Print the cell with a space separator
            }
            System.out.println(); // Move to the next line after printing a row
        }
    }

    public static void main(String[] args) {
        solveNQueens(4); // Solve the N-Queens problem for an 8x8 board
    }
}
