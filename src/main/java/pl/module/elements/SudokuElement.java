package pl.module.elements;

import java.util.Arrays;
import java.util.Collections;
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
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (element[i].getFieldValue() == element[j].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
