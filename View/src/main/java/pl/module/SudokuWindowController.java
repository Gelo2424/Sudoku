package pl.module;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;



public class SudokuWindowController {

    @FXML
    public GridPane gridPane;
    public AnchorPane mainAnchorPane;

    private final SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    private static DifficultyLevel.Difficulty difficulty;
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(sudokuSolver);


    public void initialize() throws CloneNotSupportedException {
        difficulty = MenuWindowController.getDifficulty();
        sudokuBoard.solveGame();
        sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        DifficultyLevel.prepareBoard(sudokuBoardCopy, difficulty);
        fillBoard();
    }

    private void fillBoard() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                NumberTextField textField = new NumberTextField();
                textField.setAlignment(Pos.CENTER);
                if(sudokuBoardCopy.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setOpacity(1);
                    textField.setId("cell");
                    textField.setText(String.valueOf(sudokuBoardCopy.get(i, j)));
                }
                gridPane.add(textField, j, i);
            }
        }
    }


    public void checkBoard() {

        //TODO

    }

    public void exit() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/menuWindow.fxml"));
        Stage stage = new Stage();
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
        mainAnchorPane.getScene().getWindow().hide();

    }
}
