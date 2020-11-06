package pl.module.elements;

import java.util.Arrays;
import pl.module.SudokuBoard;


public class SudokuElement {
    private final SudokuField[] element;

    public SudokuElement(SudokuField[] element) {
        if (element.length != SudokuBoard.SIZE) {
            throw new IllegalArgumentException("Wrong size. Must be " + SudokuBoard.SIZE);
        }
        this.element = element;
    }

    public boolean verify() {
        Arrays.sort(element);
        for (int i = 1; i < 10; i++) {
            if (element[i].getFieldValue() != i) {
                return false;
            }
        }
        return true;
    }
}
