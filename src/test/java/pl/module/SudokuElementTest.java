package pl.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.module.elements.SudokuBox;
import pl.module.elements.SudokuColumn;
import pl.module.elements.SudokuField;
import pl.module.elements.SudokuRow;


public class SudokuElementTest {

    @Test
    public void verifyRowTest() {
        SudokuField[] row = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            row[i - 1] = new SudokuField();
            row[i - 1].setFieldValue(i);
        }
        SudokuRow sudokuRowTest1 = new SudokuRow(row);
        assertTrue(sudokuRowTest1.verify());

        row[0].setFieldValue(2);
        SudokuRow sudokuRowTest2 = new SudokuRow(row);
        assertFalse(sudokuRowTest2.verify());
    }

    @Test
    public void verifyColumnTest() {
        SudokuField[] column = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            column[i - 1] = new SudokuField();
            column[i - 1].setFieldValue(i);
        }
        SudokuColumn sudokuColumnTest1 = new SudokuColumn(column);
        assertTrue(sudokuColumnTest1.verify());

        column[0].setFieldValue(2);
        SudokuColumn sudokuColumnTest2 = new SudokuColumn(column);
        assertFalse(sudokuColumnTest2.verify());
    }

    @Test
    public void verifyBoxTest() {
        SudokuField[] box = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            box[i - 1] = new SudokuField();
            box[i - 1].setFieldValue(i);
        }
        SudokuBox sudokuBoxTest1 = new SudokuBox(box);
        assertTrue(sudokuBoxTest1.verify());

        box[0].setFieldValue(2);
        SudokuBox sudokuBoxTest2 = new SudokuBox(box);
        assertFalse(sudokuBoxTest2.verify());
    }

    @Test
    public void wrongElementSizeTest() {
        try {
            SudokuField[] element = new SudokuField[10];
            SudokuRow row = new SudokuRow(element);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

}
