package artificial_intelligence;

import java.util.Scanner;

public class NQueensProblem {
    static int N;

    static void printSolution(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int row, int col, boolean[] rows, boolean[] leftDiags, boolean[] rightDiags) {
        return !(rows[row] || leftDiags[row + col] || rightDiags[N - 1 - col + row]);
    }

    static boolean solveNQueens(int[][] board, int col, boolean[] rows, boolean[] leftDiags, boolean[] rightDiags) {
        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(i, col, rows, leftDiags, rightDiags)) {
                rows[i] = true;
                leftDiags[i + col] = true;
                rightDiags[N - 1 - col + i] = true;
                board[i][col] = 1;

                if (solveNQueens(board, col + 1, rows, leftDiags, rightDiags)) {
                    return true;
                }

                // Backtrack
                rows[i] = false;
                leftDiags[i + col] = false;
                rightDiags[N - 1 - col + i] = false;
                board[i][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the square board: ");
        N = scanner.nextInt();

        int[][] board = new int[N][N];
        boolean[] rows = new boolean[N];
        boolean[] leftDiags = new boolean[2 * N - 1];
        boolean[] rightDiags = new boolean[2 * N - 1];

        boolean solutionExists = solveNQueens(board, 0, rows, leftDiags, rightDiags);

        if (solutionExists) {
            printSolution(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
