package pl.module.elements;

import java.util.List;

public class SudokuColumn extends SudokuElement {

    public SudokuColumn(List<SudokuField> element) {
        super(element);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SudokuColumn(this.getElement());
    }

}
