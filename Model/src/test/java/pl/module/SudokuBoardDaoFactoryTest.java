package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDaoFactory() {
        assertEquals(SudokuBoardDaoFactory.getFileDao("test").getClass(), FileSudokuBoardDao.class);
    }

}
