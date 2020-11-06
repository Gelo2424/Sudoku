package pl.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(SudokuBoard board) {
        fillBoard(board);
    }

    private boolean fillBoard(SudokuBoard board) {
        int[] temp = findEmpty(board);
        if (temp == null) {
            return true;
        }
        int row = temp[0];
        int col = temp[1];
        ArrayList<Integer> numbers = initializeNumbers();
        for (int num : numbers) {
            if (valid(board, num, row, col)) {
                board.set(row, col, num);
                if (fillBoard(board)) {
                    return true;
                }
                board.set(row, col, 0);
            }
        }
        return false;
    }

    private boolean valid(SudokuBoard board, int num, int row, int col) {
        //ROW
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            if (board.get(row, i) == num && col != i) {
                return false;
            }
        }
        //COLUMN
        for (int j = 0; j < SudokuBoard.SIZE; j++) {
            if (board.get(j, col) == num && row != j) {
                return false;
            }
        }
        //SQUARE
        int squareX = row / 3;
        int squareY = col / 3;
        for (int i = squareX * 3; i < squareX * 3 + 3; i++) {
            for (int j = squareY * 3; j < squareY * 3 + 3; j++) {
                if (board.get(i, j) == num && i != row && j != col) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findEmpty(SudokuBoard board) {
        int []temp = new int[2];
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                if (board.get(i, j) == 0) {
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> initializeNumbers() {
        Integer[] nums = {1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(nums));
        Collections.shuffle(temp);
        return temp;
    }

}
