package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {

    private final SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private Dao<SudokuBoard> fileSudokuBoardDao;

    @Test
    public void writeAndReadTest() throws Exception {
        try(Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("test.txt")){
            fileSudokuBoardDao.write(board);
            SudokuBoard board2 = fileSudokuBoardDao.read();
            assertEquals(board, board2);
        }
    }

    @Test
    public void readIOExceptionTest()  throws Exception{
        try(Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("test2")){
            assertThrows(DaoException.class, fileSudokuBoardDao::read);
        }
    }

    @Test
    public void writeIOExceptionTest() throws Exception {
        try(Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("...")){
            assertThrows(DaoException.class, () -> {
                fileSudokuBoardDao.write(board);
            });
        }


    }

}
