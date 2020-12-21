package pl.module;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text) && !super.getText().matches("[1-9]")) {
            super.replaceText(start, end, text);
        } else if (validate(text) && super.getText().matches("[1-9]")) {
            super.setText(text);
        }
        if (text.matches("")) {
            super.setText("");
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return text.matches("[1-9]");
    }
}