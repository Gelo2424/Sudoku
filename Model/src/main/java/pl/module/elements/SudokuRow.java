package pl.module.elements;

import java.util.List;

public class SudokuRow extends SudokuElement {

    public SudokuRow(List<SudokuField> element) {
        super(element);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SudokuRow(this.getElement());
    }

}
