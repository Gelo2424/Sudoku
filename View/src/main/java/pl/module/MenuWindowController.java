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
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuWindowController {

    private static DifficultyLevel.Difficulty difficulty;
    private ObservableList<String> difficulties;
    private ObservableList<String> languages;
    public ChoiceBox<String> difficultyChoiceBox;
    public ChoiceBox<String> langChoiceBox;
    public Label difficultyLevel;
    public AnchorPane mainAnchorPane;
    public static ResourceBundle bundle;

    @FXML
    public void initialize() {

        difficulties = FXCollections.observableArrayList(
                bundle.getString("diffLevel01"), bundle.getString("diffLevel02"), bundle.getString("diffLevel03"));
        languages = FXCollections.observableArrayList(
                bundle.getString("lang01"), bundle.getString("lang02"));

        difficultyChoiceBox.setValue("");
        if(bundle.getLocale().equals(new Locale("en"))) {
            langChoiceBox.setValue("english");
        }
        else{
            langChoiceBox.setValue("polski");
        }
        difficultyChoiceBox.setItems(difficulties);
        langChoiceBox.setItems(languages);

        difficultyChoiceBox.getSelectionModel().selectedIndexProperty().addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                difficultyLevel.setText(bundle.getString("diffLevel01"));
                difficultyLevel.setStyle("-fx-text-fill: #0d880d");
                difficulty = DifficultyLevel.Difficulty.EASY;
            }
            else if (t1.equals(1)) {
                difficultyLevel.setText(bundle.getString("diffLevel02"));
                difficultyLevel.setStyle("-fx-text-fill: #e5a40d");
                difficulty = DifficultyLevel.Difficulty.MEDIUM;
            }
            else if (t1.equals(2)) {
                difficultyLevel.setText(bundle.getString("diffLevel03"));
                difficultyLevel.setStyle("-fx-text-fill: #bf0303");
                difficulty = DifficultyLevel.Difficulty.HARD;
            }
        })));

        langChoiceBox.getSelectionModel().selectedIndexProperty().addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                if(bundle.getLocale().equals(new Locale("en"))){
                    try {
                        reload(new Locale("pl"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (t1.equals(1)) {
                if(bundle.getLocale().equals(new Locale("pl"))){
                    try {
                        reload(new Locale("en"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        })));

    }

    public void startGame() throws IOException {
        if(difficultyChoiceBox.getValue().equals("")) {
            DialogBox.showMessage(bundle.getString("noDiffLevel"), Alert.AlertType.WARNING);
            return;
        }
        AnchorPane anchorPane = FXMLLoader.load(this.getClass()
                .getResource("/fxml/sudokuWindow.fxml"), bundle);
        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("/styles/boardStyle.css");
        stage.setScene(scene);
        stage.show();
        mainAnchorPane.getScene().getWindow().hide();
    }

    public void reload(Locale loc) throws IOException {
        bundle = ResourceBundle.getBundle("language", loc);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass()
                .getResource("/fxml/menuWindow.fxml"), bundle);
        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("/styles/boardStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public void showAuthors() {
        ResourceBundle authorsList = ResourceBundle.getBundle("pl.module.Authors", bundle.getLocale());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle((String)authorsList.getObject("title"));
        alert.setContentText(authorsList.getObject("author1") + "\n" + authorsList.getObject("author2"));
        alert.showAndWait();
    }

    public static DifficultyLevel.Difficulty getDifficulty() {
        return difficulty;
    }


}
