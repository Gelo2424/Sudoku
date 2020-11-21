package pl.module.elements;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(element).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(element, ((SudokuElement) obj).element).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fields", element).toString();
    }
}
