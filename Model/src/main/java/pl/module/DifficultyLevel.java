package pl.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DifficultyLevel {

    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    private DifficultyLevel() {
    }

    public static void prepareBoard(SudokuBoard bo, Difficulty difficulty) {
        int size = SudokuBoard.SIZE;
        int cells = size * 9;

        double howManyBlanks;

        if (difficulty == Difficulty.EASY) {
            howManyBlanks = 0.30;
        } else if (difficulty == Difficulty.MEDIUM) {
            howManyBlanks = 0.50;
        } else {
            howManyBlanks = 0.70;
        }

        int blankPerRow = (int)((cells * howManyBlanks) / size);
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> nums = randNumbers(blankPerRow);
            for (int j = 0; j < 9; j++) {
                if (!nums.contains(j + 1)) {
                    bo.set(i, j, 0);
                }
            }
        }
    }

    private static ArrayList<Integer> randNumbers(int blanks) {
        Integer[] nums = {1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(nums));
        Collections.shuffle(temp);
        temp.subList(temp.size() - blanks, temp.size()).clear();
        return temp;
    }

}
