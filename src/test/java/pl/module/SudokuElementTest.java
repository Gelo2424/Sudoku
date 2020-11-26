package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.module.elements.SudokuBox;
import pl.module.elements.SudokuColumn;
import pl.module.elements.SudokuField;
import pl.module.elements.SudokuRow;

public class SudokuElementTest {

    @Test
    public void verifyRowTest() {
        List<SudokuField> row = Arrays.asList(new SudokuField[SudokuBoard.SIZE]);
        for (int i = 1; i < 10; i++) {
            row.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRowTest1 = new SudokuRow(row);
        assertTrue(sudokuRowTest1.verify());

        row.set(0, new SudokuField(2));
        SudokuRow sudokuRowTest2 = new SudokuRow(row);
        assertFalse(sudokuRowTest2.verify());
    }

    @Test
    public void verifyColumnTest() {
        List<SudokuField> column = Arrays.asList(new SudokuField[SudokuBoard.SIZE]);
        for (int i = 1; i < 10; i++) {
            column.set(i - 1, new SudokuField(i));
        }
        SudokuColumn sudokuColumnTest1 = new SudokuColumn(column);
        assertTrue(sudokuColumnTest1.verify());

        column.set(0, new SudokuField(2));
        SudokuColumn sudokuColumnTest2 = new SudokuColumn(column);
        assertFalse(sudokuColumnTest2.verify());
    }

    @Test
    public void verifyBoxTest() {
        List<SudokuField> box = Arrays.asList(new SudokuField[SudokuBoard.SIZE]);
        for (int i = 1; i < 10; i++) {
            box.set(i - 1, new SudokuField(i));
        }
        SudokuBox sudokuBoxTest1 = new SudokuBox(box);
        assertTrue(sudokuBoxTest1.verify());

        box.set(0, new SudokuField(2));
        SudokuBox sudokuBoxTest2 = new SudokuBox(box);
        assertFalse(sudokuBoxTest2.verify());
    }

    @Test
    public void wrongElementSizeTest() {
        try {
            List<SudokuField> element = Arrays.asList(new SudokuField[10]);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getElementTest() {
        List<SudokuField> row = Arrays.asList(new SudokuField[SudokuBoard.SIZE]);
        for (int i = 1; i < 10; i++) {
            row.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRowTest1 = new SudokuRow(row);
        assertSame(sudokuRowTest1.getElement(), row);
    }

    @Test
    public void toStringTest() {
        List<SudokuField> element = Arrays.asList(new SudokuField[9]);
        SudokuRow row = new SudokuRow(element);
        assertNotNull(row.toString());
    }

    @Test
    public void equalsTest() {
        List<SudokuField> element1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> element2 = Arrays.asList(new SudokuField[9]);
        SudokuRow row1 = new SudokuRow(element1);
        SudokuRow row2 = new SudokuRow(element2);
        assertTrue(row1.equals(row2) && row2.equals(row1));
        assertTrue((row1.equals(row1)));
        assertFalse((row1.equals("")));
        assertFalse(row1.equals(null));
    }

    @Test
    public void hashCodeTest() {
        List<SudokuField> element1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> element2 = Arrays.asList(new SudokuField[9]);
        SudokuRow row1 = new SudokuRow(element1);
        SudokuRow row2 = new SudokuRow(element2);
        assertEquals(row1.hashCode(), row2.hashCode());
    }

    @Test
    public void equalAndHasCodeTest() {
        List<SudokuField> element1 = Arrays.asList(new SudokuField[9]);
        List<SudokuField> element2 = Arrays.asList(new SudokuField[9]);
        SudokuRow row = new SudokuRow(element1);
        SudokuColumn column = new SudokuColumn(element2);

        assertEquals(row.hashCode(), column.hashCode());
        assertFalse(row.equals(column));
    }

}
