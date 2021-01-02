package pl.module.exceptions;

public class WriteFileException extends DaoException {
    public WriteFileException(Throwable e) {
        super(e);
    }

    public WriteFileException(String message, Throwable e) {
        super(message, e);
    }
}
