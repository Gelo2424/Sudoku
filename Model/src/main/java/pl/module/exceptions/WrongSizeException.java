package pl.module.exceptions;

public class WrongSizeException extends IllegalArgumentException {

    public WrongSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongSizeException(String s) {
        super(s);
    }

    public WrongSizeException(Throwable cause) {
        super(cause);
    }
}
