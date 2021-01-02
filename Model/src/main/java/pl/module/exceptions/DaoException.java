package pl.module.exceptions;

public class DaoException extends Exception {
    public DaoException(Throwable e) {
        super(e);
    }

    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
