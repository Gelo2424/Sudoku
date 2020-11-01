package pl.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    public void setAndGetElementTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        assertEquals(sudokuBoard.getElement(0,0), 0);
        sudokuBoard.setElement(0,0,6);
        assertEquals(sudokuBoard.getElement(0,0), 6);
    }

    @Test
    public void isBoardValidTest() {
        SudokuBoard testSudokuBoard1 = new SudokuBoard();
        SudokuBoard testSudokuBoard2 = new SudokuBoard();
        SudokuBoard testSudokuBoard3 = new SudokuBoard();
        testSudokuBoard1.solveGame();
        testSudokuBoard2.solveGame();
        testSudokuBoard3.solveGame();

        //plansza poprawna
        assertTrue(testSudokuBoard1.isBoardValid());

        //blad w wierszu
        testSudokuBoard1.setElement(0,0, testSudokuBoard1.getElement(0, 1));
        assertFalse(testSudokuBoard1.isBoardValid());

        //blad w kolumnie
        int temp = testSudokuBoard2.getElement(2,1);
        testSudokuBoard2.setElement(2, 1, testSudokuBoard2.getElement(2, 0));
        testSudokuBoard2.setElement(2,0, temp);
        assertFalse(testSudokuBoard2.isBoardValid());

        //blad w kwadracie
        for (int i = 0; i < 9; i ++) {
            temp = testSudokuBoard3.getElement(2, i);
            testSudokuBoard3.setElement(2, i, testSudokuBoard3.getElement(3, i));
            testSudokuBoard3.setElement(3, i, temp);
        }
        assertFalse(testSudokuBoard3.isBoardValid());
    }

}

