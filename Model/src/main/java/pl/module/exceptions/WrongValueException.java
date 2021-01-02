package pl.module.exceptions;

public class WrongValueException extends IllegalArgumentException {

    public WrongValueException(Throwable cause) {
        super(cause);
    }

    public WrongValueException(String s) {
        super(s);
    }

    public WrongValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
