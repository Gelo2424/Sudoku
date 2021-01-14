package pl.module.exceptions;

public class JdbcException extends DaoException {
    public JdbcException(Throwable e) {
        super(e);
    }

    public JdbcException(String message, Throwable e) {
        super(message, e);
    }

}
