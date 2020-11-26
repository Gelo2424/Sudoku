package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {

    private final SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private final SudokuBoardDaoFactory daoFactory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;

    @Test
    public void writeAndReadTest() {
        fileSudokuBoardDao = daoFactory.getFileDao("test.txt");
        fileSudokuBoardDao.write(board);
        SudokuBoard board2 = fileSudokuBoardDao.read();

       assertEquals(board, board2);
    }

    @Test
    public void readIOExceptionTest() {
        fileSudokuBoardDao = daoFactory.getFileDao("test2");
        assertThrows(RuntimeException.class, () -> {
            fileSudokuBoardDao.read();
        });
    }

    @Test
    public void writeIOExceptionTest() {
        fileSudokuBoardDao = daoFactory.getFileDao("...");
        assertThrows(RuntimeException.class, () -> {
            fileSudokuBoardDao.write(board);
        });
    }

}
