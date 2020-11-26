package pl.module;

public interface Dao<T> {
    T read();

    void write(T obj);
}

