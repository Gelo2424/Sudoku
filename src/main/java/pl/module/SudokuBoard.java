package pl.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SudokuBoard {

    private static final int SIZE = 9;
    private final int[][] board = new int[SIZE][SIZE];

    public boolean fillBoard() {
        int[] temp = findEmpty();
        if (temp == null) {
            return true;
        }
        int row = temp[0];
        int col = temp[1];
        ArrayList<Integer> numbers = initializeNumbers();
        for (int num : numbers) {
            if (valid(num, row, col)) {
                board[row][col] = num;
                if (fillBoard()) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean valid(int num, int row, int col) {
        //ROW
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num && col != i) {
                return false;
            }
        }
        //COLUMN
        for (int j = 0; j < SIZE; j++) {
            if (board[j][col] == num && row != j) {
                return false;
            }
        }
        //SQUARE
        int squareX = row / 3;
        int squareY = col / 3;
        for (int i = squareX * 3; i < squareX * 3 + 3; i++) {
            for (int j = squareY * 3; j < squareY * 3 + 3; j++) {
                if (board[i][j] == num && i != row && j != col) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findEmpty() {
        int []temp = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
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

    public int getElement(int row, int col) {
        return board[row][col];
    }
}
