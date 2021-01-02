package pl.module;

import pl.module.exceptions.DaoException;

public interface Dao<T> extends AutoCloseable {
    T read() throws DaoException;

    void write(T obj) throws DaoException;
}

