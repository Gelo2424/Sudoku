package pl.module.elements;

public class SudokuField {

    private int value;

    public SudokuField() {
        this.setFieldValue(value);
    }

    public SudokuField(int value) {
        this.setFieldValue(value);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Invalid number. Valid Range<0-9>");
        }
        this.value = value;
    }

}
