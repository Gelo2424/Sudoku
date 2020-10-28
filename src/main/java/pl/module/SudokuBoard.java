package pl.module;

import java.util.Arrays;

public class SudokuBoard {

    public static final int SIZE = 9;
    private final int[][] board = new int[SIZE][SIZE];
    private final SudokuSolver sudokuSolver;

    SudokuBoard() {
        sudokuSolver = new BacktrackingSudokuSolver();
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public int getElement(int row, int col) {
        return board[row][col];
    }

    public void setElement(int row, int col, int value) {
        this.board[row][col] = value;
    }

    public boolean isBoardValid() {
        //rows and columns
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (board[i][j] == board[i][k]) {
                        return false;
                    }
                }
                for (int k = i + 1; k < 9; k++) {
                    if (board[i][j] == board[k][j]) {
                        return false;
                    }
                }
            }
        }
        //squares
        int counter = 0;
        int[] checkBoard = new int[9];
        int[] checkList = {1,2,3,4,5,6,7,8,9};

        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                for (int r = startRow; r < startRow + 3; r++) {
                    for (int c = startCol; c < startCol + 3; c++) {
                        checkBoard[counter++] = board[r][c];
                    }
                }
                counter = 0;
                Arrays.sort(checkBoard);
                if (!Arrays.equals(checkBoard, checkList)) {
                    return false;
                }
            }
        }
        return true;
    }

}
