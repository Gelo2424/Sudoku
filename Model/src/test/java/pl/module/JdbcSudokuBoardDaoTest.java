//package pl.module;
//
//import org.junit.jupiter.api.*;
//import pl.module.exceptions.JdbcException;
//import pl.module.exceptions.WriteFileException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class JdbcSudokuBoardDaoTest {
//
//    static SudokuBoard sudokuBoard;
//    static SudokuBoard sudokuBoardTemplate;
//    static SudokuBoard sudokuBoardSolved;
//
//    @BeforeAll
//    static void setUp() throws CloneNotSupportedException, JdbcException {
//        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
//        sudokuBoard.solveGame();
//        sudokuBoardSolved = (SudokuBoard) sudokuBoard.clone();
//        DifficultyLevel.prepareBoard(sudokuBoard, DifficultyLevel.Difficulty.EASY);
//        sudokuBoardTemplate = (SudokuBoard) sudokuBoard.clone();
//
//    }
//
//    @AfterAll
//    static void afterAll() throws Exception {
//        try(JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("testBoard")) {
//            dao.deleteTestBoard();
//        }
//    }
//
//    @Test
//    public void writeAndReadSudokuTest() throws Exception {
//        try(Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("testBoard")) {
//            JdbcSudokuBoardDao.numOfBoard = 0;
//            dao.write(sudokuBoard);
//            dao.write(sudokuBoardTemplate);
//            dao.write(sudokuBoardSolved);
//            assertEquals(sudokuBoard, dao.read());
//            assertEquals(sudokuBoardTemplate, dao.read());
//            assertEquals(sudokuBoardSolved, dao.read());
//        }
//        try(Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("testBoard")) {
//            assertThrows(JdbcException.class, () -> {
//                dao.write(sudokuBoard);
//            });
//        }
//    }
//    @Test
//    public void readWrongNameTest() throws Exception {
//        JdbcSudokuBoardDao.numOfBoard = 0;
//        try(Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("testBoard2")) {
//            assertThrows(JdbcException.class, () -> {
//                dao.read();
//            });
//        }
//    }
//
//}
