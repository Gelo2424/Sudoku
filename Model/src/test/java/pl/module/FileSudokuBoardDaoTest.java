package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import pl.module.exceptions.ReadFileException;
import pl.module.exceptions.WriteFileException;

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
            assertThrows(ReadFileException.class, fileSudokuBoardDao::read);
        }
    }

    @Test
    public void writeIOExceptionTest() throws Exception {
        try(Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("...")){
            assertThrows(WriteFileException.class, () -> {
                fileSudokuBoardDao.write(board);
            });
        }


    }

}
