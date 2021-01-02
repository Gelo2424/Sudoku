package pl.module;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.module.exceptions.ReadFileException;
import pl.module.exceptions.WriteFileException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws ReadFileException {
        SudokuBoard board;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(fileName))) {
            board = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new ReadFileException("Cant load a sudoku file", e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard board) throws WriteFileException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(board);
        } catch (IOException e) {
            throw new WriteFileException("Cant write a sudoku file", e);
        }
    }

    @Override
    public void close() {

    }
}
