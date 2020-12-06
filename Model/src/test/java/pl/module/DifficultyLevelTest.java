package pl.module;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifficultyLevelTest {

    DifficultyLevel.Difficulty diffEasy = DifficultyLevel.Difficulty.EASY;
    DifficultyLevel.Difficulty diffMedium = DifficultyLevel.Difficulty.MEDIUM;
    DifficultyLevel.Difficulty diffHard = DifficultyLevel.Difficulty.HARD;

    @Test
    public void setDifficultyTest() {

        assertEquals(diffEasy, DifficultyLevel.Difficulty.EASY);
        assertEquals(diffMedium, DifficultyLevel.Difficulty.MEDIUM);
        assertEquals(diffHard, DifficultyLevel.Difficulty.HARD);
    }

    @Test
    public void prepareBoardTest() {
        SudokuBoard bo = new SudokuBoard(new BacktrackingSudokuSolver());
        bo.solveGame();
        DifficultyLevel.prepareBoard(bo, diffEasy);
        for(int i = 0; i < 9; i++) {
            int counter = 0;
            for(int j = 0; j < 9; j++) {
               if(bo.get(i, j) == 0) {
                   counter++;
               }
            }
            assertEquals(counter, 2);
        }
        bo.solveGame();
        DifficultyLevel.prepareBoard(bo, diffMedium);
        for(int i = 0; i < 9; i++) {
            int counter = 0;
            for(int j = 0; j < 9; j++) {
                if(bo.get(i, j) == 0) {
                    counter++;
                }
            }
            assertEquals(counter, 4);
        }
        bo.solveGame();
        DifficultyLevel.prepareBoard(bo, diffHard);
        for(int i = 0; i < 9; i++) {
            int counter = 0;
            for(int j = 0; j < 9; j++) {
                if(bo.get(i, j) == 0) {
                    counter++;
                }
            }
            assertEquals(counter, 6);
        }
    }

}
