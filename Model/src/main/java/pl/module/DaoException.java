package pl.module;

import java.io.IOException;

public class DaoException extends Exception{
    public DaoException(Exception e) {
        super(e);
    }
}
