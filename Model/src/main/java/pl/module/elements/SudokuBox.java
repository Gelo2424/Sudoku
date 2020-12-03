package pl.module.elements;

import java.util.List;

public class SudokuBox extends SudokuElement {

    public SudokuBox(List<SudokuField> element) {
        super(element);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SudokuBox(this.getElement());
    }
}
