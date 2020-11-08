package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.module.elements.SudokuField;

public class SudokuFieldTest {

    private final SudokuField sudokuField = new SudokuField();

    @Test
    public void getFieldValueTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
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
    }
}
