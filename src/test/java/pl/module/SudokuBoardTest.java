package pl.module;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import org.junit.jupiter.api.Test;


public class SudokuBoardTest {


    @Test //czy tablica wygenerowana poprawnie
    public void fillBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        //int[][] bo = sudokuBoard.getCopyOfBoard();

        //rows
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(sudokuBoard.getElement(i, j)
                            == sudokuBoard.getElement(i, k) && k != j);
                }
            }
        }
        //columns
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(sudokuBoard.getElement(i, j)
                            == sudokuBoard.getElement(k, j) && k != i);
                }
            }
        }
        //squares
        int inboardcounter = 0;
        int[] checkBoard = new int[9];
        int[] checkList = {1,2,3,4,5,6,7,8,9};

        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                for (int r = startRow; r < startRow + 3; r++) {
                    for (int c = startCol; c < startCol + 3; c++) {
                        checkBoard[inboardcounter++] = sudokuBoard.getElement(r,c);
                    }
                }
                inboardcounter = 0;
                Arrays.sort(checkBoard);
                for (int c = 0; c < 9; c++) {
                    assertArrayEquals(checkBoard, checkList);
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
        //int[][] bo1 = sudokuBoard.getCopyOfBoard();
        //int[][] bo2 = sudokuBoard1.getCopyOfBoard();

        boolean theSame = true;

        int[] tempBoard1 = new int[9];
        int[] tempBoard2 = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 tempBoard1[j] = sudokuBoard.getElement(i, j);
                 tempBoard2[j] = sudokuBoard1.getElement(i, j);
            }
            if (!Arrays.equals(tempBoard1, tempBoard2)) {
                theSame = false;
                break;
            }
        }
        assertFalse(theSame);
    }
}

