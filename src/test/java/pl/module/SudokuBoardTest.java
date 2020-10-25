package pl.module;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class SudokuBoardTest {


    @Test //czy tablica wygenerowana poprawnie
    public void fillBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        int[][] bo = sudokuBoard.getCopyOfBoard();

        //rows
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(bo[i][j] == bo[i][k] && k != j);
                }
            }
        }
        //columns
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(bo[i][j] == bo[k][j] && k != i);
                }
            }
        }
        //squares
        int inboardcounter =0;
        int[] checkboard = new int[9];
        int[] checklist = {1,2,3,4,5,6,7,8,9};


        for (int start_row = 0; start_row < 9; start_row += 3) {
            for (int start_col = 0; start_col < 9; start_col += 3) {
                for (int r = start_row; r < start_row + 3; r++) {
                    for (int c = start_col; c < start_col + 3; c++) {
                        checkboard[inboardcounter++] = bo[r][c];
                    }
                }
                inboardcounter = 0;
                Arrays.sort(checkboard);
                for (int c = 0; c < 9; c++) {
                    assertArrayEquals(checkboard, checklist);
                }
            }
        }
    }



    @Test //czy dwa kolejne wywołania fillBoard generuja inny układ
    public void fillBoardTwiceTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        sudokuBoard.fillBoard();
        sudokuBoard1.fillBoard();
        int[][] bo1 = sudokuBoard.getCopyOfBoard();
        int[][] bo2 = sudokuBoard1.getCopyOfBoard();

        boolean thesame = true;

        for (int i = 0; i < 9; i++ ) {
            int[] tempboard1 = bo1[i];
            int[] tempboard2 = bo2[i];

            if (!Arrays.equals(tempboard1, tempboard2)) {
                thesame = false;
                break;
            }
        }

        assertFalse(thesame);

    }
}

