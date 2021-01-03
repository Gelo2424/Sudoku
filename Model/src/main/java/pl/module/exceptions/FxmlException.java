package pl.module.exceptions;

import java.io.IOException;

public class FxmlException extends IOException {

    public FxmlException(String message) {
        super(message);
    }

    public FxmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FxmlException(Throwable cause) {
        super(cause);
    }
}
