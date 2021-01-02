package pl.module.exceptions;

import java.io.IOException;

public class FxmlNotFoundException extends IOException {

    public FxmlNotFoundException(String message) {
        super(message);
    }

    public FxmlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FxmlNotFoundException(Throwable cause) {
        super(cause);
    }
}
