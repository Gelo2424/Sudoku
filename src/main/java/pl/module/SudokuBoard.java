package pl.module;

import pl.module.elements.SudokuBox;
import pl.module.elements.SudokuColumn;
import pl.module.elements.SudokuField;
import pl.module.elements.SudokuRow;

public class SudokuBoard {

    public static final int SIZE = 9;
    private final SudokuSolver sudokuSolver;
    private final SudokuField[][] board = new SudokuField[SIZE][SIZE];


    SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
        checkBoard();

    }

    public SudokuRow getRow(int y) {
        SudokuField[] row = new SudokuField[SIZE];
        System.arraycopy(board[y], 0, row, 0, SIZE);
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[SIZE];
        for (int i = 0; i < 9; i++) {
            column[i] = board[i][x];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[SIZE];
        int counter = 0;
        int squareX = x / 3;
        int squareY = y / 3;
        for (int i = squareX * 3; i < squareX * 3 + 3; i++) {
            for (int j = squareY * 3; j < squareY * 3 + 3; j++) {
                box[counter++] = board[i][j];
            }
        }
        return new SudokuBox(box);
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    private boolean checkBoard() {
        //rows and columns
        for (int i = 0; i < 9; i++) {
            SudokuRow row = getRow(i);
            SudokuColumn col = getColumn(i);
            if (!row.verify() || !col.verify()) {
                return false;
            }

        }
        //boxes
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                SudokuBox box = getBox(i, j);
                if (!box.verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkBoardForTests() {
        return checkBoard();
    }
}
