package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.module.elements.SudokuBox;
import pl.module.elements.SudokuColumn;
import pl.module.elements.SudokuRow;


public class SudokuBoardTest {

    private final BacktrackingSudokuSolver solver;
    private final SudokuBoard board;


    public SudokuBoardTest() {
        this.solver = new BacktrackingSudokuSolver();
        this.board = new SudokuBoard(solver);
        board.solveGame();
    }

    @Test
    public void setAndGetElementTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        assertEquals(sudokuBoard.get(0,0), 0);
        sudokuBoard.set(0,0,6);
        assertEquals(sudokuBoard.get(0,0), 6);
    }

    @Test
    public void getRowTest() {
        SudokuRow row = board.getRow(0);
        assertTrue(row.verify());

        SudokuBoard board2 = board;
        board2.set(0, 1, board2.get(0,0));
        row = board2.getRow(0);
        assertFalse(row.verify());
    }

    @Test
    public void getColumnTest() {
        SudokuColumn column = board.getColumn(0);
        assertTrue(column.verify());

        SudokuBoard board2 = board;
        board2.set(1, 0, board2.get(0,0));
        column = board2.getColumn(0);
        assertFalse(column.verify());
    }

    @Test
    public void getBoxTest() {
        SudokuBox box = board.getBox(0, 0);
        assertTrue(box.verify());

        SudokuBoard board2 = board;
        for (int i = 0; i < 9; i++) {
            int temp = board2.get(2, i);
            board2.set(2, i, board2.get(3, i));
            board2.set(3, i, temp);
        }
        box = board2.getBox(3, 0);
        assertFalse(box.verify());
    }

    @Test
    public void checkBoardTest() {
        SudokuBoard board1 = board;
        for (int i = 0; i < 9; i++) {
            int temp = board1.get(2, i);
            board1.set(2, i, board1.get(3, i));
            board1.set(3, i, temp);
        }
        assertFalse(board1.checkBoardForTests());

        SudokuBoard board2 = board;
        board2.set(1, 0, board2.get(0,0));
        assertFalse(board2.checkBoardForTests());

        SudokuBoard board3 = board;
        board3.set(0, 1, board3.get(0,0));
        assertFalse(board3.checkBoardForTests());
    }

    @Test
    public void toStringTest() {
        assertNotNull(board.toString());
    }

    @Test
    public void equalsTest() {
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        assertTrue(board1.equals(board2));
        board1.solveGame();
        board2.solveGame();
        assertFalse(board1.equals(board2));
        assertFalse(board1.equals(null));
        assertTrue(board1.equals(board1));
        assertFalse(board1.equals("board2"));
    }

    @Test
    public void hashCodeTest() {
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        assertEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void equalAndHasCodeTest() {
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        assertTrue(board1.equals(board2));
        assertEquals(board1.hashCode(), board2.hashCode());
    }

}

