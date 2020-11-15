package pl.module.elements;

import java.util.List;
import pl.module.SudokuBoard;

public class SudokuElement {
    private final List<SudokuField> element;

    public SudokuElement(List<SudokuField> element) {
        if (element.size() != SudokuBoard.SIZE) {
            throw new IllegalArgumentException("Wrong size. Must be " + SudokuBoard.SIZE);
        }
        this.element = element;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (element.get(i).getFieldValue() == element.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<SudokuField> getElement() {
        return element;
    }
}
