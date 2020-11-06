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
    }

    public void solveGame() {
        sudokuSolver.solve(this);
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



    /*
    public boolean checkboard() {
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
    */
}
