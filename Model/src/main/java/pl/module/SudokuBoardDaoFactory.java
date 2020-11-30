package pl.module;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {
    }

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
