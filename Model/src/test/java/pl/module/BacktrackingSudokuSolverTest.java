package pl.module;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BacktrackingSudokuSolverTest {

    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    @Test //czy tablica wygenerowana poprawnie
    public void fillBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        //rows
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(sudokuBoard.get(i, j)
                            == sudokuBoard.get(i, k) && k != j);
                }
            }
        }
        //columns
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    assertFalse(sudokuBoard.get(i, j)
                            == sudokuBoard.get(k, j) && k != i);
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
                        checkBoard[inboardcounter++] = sudokuBoard.get(r,c);
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
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        SudokuBoard sudokuBoard1 = new SudokuBoard(solver);
        sudokuBoard.solveGame();
        sudokuBoard1.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 if (sudokuBoard.get(i,j) != sudokuBoard1.get(i,j)) {
                     assertNotEquals(sudokuBoard1.get(i, j), sudokuBoard.get(i, j));
                     return;
                 }
            }
        }
        throw new AssertionError("Tablice identyczne");
    }

}
