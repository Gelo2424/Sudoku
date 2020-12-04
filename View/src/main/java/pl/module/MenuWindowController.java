package pl.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuWindowController {

    public static DifficultyLevel.Difficulty difficulty;
    private final ObservableList<String> difficulties = FXCollections.observableArrayList("easy", "medium", "hard");
    public ChoiceBox<String> difficultyChoiceBox;
    public Label difficultyLevel;
    public AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {
        difficultyChoiceBox.setValue("");
        difficultyChoiceBox.setItems(difficulties);

        difficultyChoiceBox.getSelectionModel().selectedIndexProperty().addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                difficultyLevel.setText("Easy");
                difficultyLevel.setStyle("-fx-text-fill: #0d880d");
                difficulty = DifficultyLevel.Difficulty.EASY;
            }
            else if (t1.equals(1)) {
                difficultyLevel.setText("Medium");
                difficultyLevel.setStyle("-fx-text-fill: #e5a40d");
                difficulty = DifficultyLevel.Difficulty.MEDIUM;
            }
            else if (t1.equals(2)) {
                difficultyLevel.setText("Hard");
                difficultyLevel.setStyle("-fx-text-fill: #bf0303");
                difficulty = DifficultyLevel.Difficulty.HARD;
            }
        })));

    }

    public void startGame() throws IOException {
        if(difficultyChoiceBox.getValue().equals("")) {
            DialogBox.showMessage("The difficulty level wasn't chosen", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/sudokuWindow.fxml"));
        Stage stage = new Stage();
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
        mainAnchorPane.getScene().getWindow().hide();
    }

    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }

    public static DifficultyLevel.Difficulty getDifficulty() {
        return difficulty;
    }
}
