package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.module.elements.SudokuField;

public class SudokuFieldTest {

    private final SudokuField sudokuField = new SudokuField();

    @Test
    public void getFieldValueTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
        System.out.println(this);
    }

    @Test
    public void setFieldValueTest() {
        sudokuField.setFieldValue(9);
        assertEquals(sudokuField.getFieldValue(), 9);
    }

    @Test
    public void setInvalidValueTest() {
        try {
            sudokuField.setFieldValue(10);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            sudokuField.setFieldValue(0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void toStringTest() {
        assertNotNull(sudokuField.toString());
    }

    @Test
    public void equalsTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertTrue(sudokuField1.equals(sudokuField2) && sudokuField2.equals(sudokuField1));
    }

    @Test
    public void hashCodeTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }


}
