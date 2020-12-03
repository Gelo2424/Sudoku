package pl.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class MenuWindowController {

    private final ObservableList<String> difficulty = FXCollections.observableArrayList("easy", "medium", "hard");
    public ChoiceBox<String> difficultyChoiceBox;
    public Label difficultyLevel;

    @FXML
    public void initialize() {
        difficultyChoiceBox.setValue("");
        difficultyChoiceBox.setItems(difficulty);

        difficultyChoiceBox.getSelectionModel().selectedIndexProperty().addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                difficultyLevel.setText("Easy");
                difficultyLevel.setStyle("-fx-text-fill: #0d880d");
            }
            else if (t1.equals(1)) {
                difficultyLevel.setText("Medium");
                difficultyLevel.setStyle("-fx-text-fill: #e5a40d");
            }
            else if (t1.equals(2)) {
                difficultyLevel.setText("Hard");
                difficultyLevel.setStyle("-fx-text-fill: #bf0303");
            }
        })));

    }

    public void startGame(ActionEvent actionEvent) {
    }
}
