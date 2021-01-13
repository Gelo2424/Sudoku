package pl.module;

import pl.module.exceptions.JdbcException;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {
    }

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getJdbcDao(String name) throws JdbcException {
        return new JdbcSudokuBoardDao(name);
    }
}
