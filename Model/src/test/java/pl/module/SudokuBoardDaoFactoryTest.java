package pl.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDaoFactory() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertEquals(factory.getFileDao("test").getClass(), FileSudokuBoardDao.class);
    }

}
