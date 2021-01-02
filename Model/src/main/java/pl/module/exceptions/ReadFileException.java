package pl.module.exceptions;

public class ReadFileException extends DaoException {
    public ReadFileException(Throwable e) {
        super(e);
    }

    public ReadFileException(String message, Throwable e) {
        super(message, e);
    }
}
