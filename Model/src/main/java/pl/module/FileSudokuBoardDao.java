package pl.module;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard board;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(fileName))) {
            board = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard board) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(board);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
