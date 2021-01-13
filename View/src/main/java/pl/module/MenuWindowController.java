package pl.module;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.module.exceptions.FxmlException;


public class MenuWindowController {

    private static final Logger logger
            = LoggerFactory.getLogger(MenuWindowController.class.getName());
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

        if (bundle.getLocale().equals(new Locale("en"))) {
            langChoiceBox.setValue("english");
        } else {
            langChoiceBox.setValue("polski");
        }

        difficulties = FXCollections.observableArrayList(
                bundle.getString("diffLevel01"),
                bundle.getString("diffLevel02"),
                bundle.getString("diffLevel03"));
        languages = FXCollections.observableArrayList(
                bundle.getString("lang01"),
                bundle.getString("lang02"));

        difficultyChoiceBox.setValue("");

        difficultyChoiceBox.setItems(difficulties);
        langChoiceBox.setItems(languages);

        difficultyChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                difficultyLevel.setText(bundle.getString("diffLevel01"));
                difficultyLevel.setStyle("-fx-text-fill: #0d880d");
                difficulty = DifficultyLevel.Difficulty.EASY;
                logger.info("Difficulty: easy was chosen");
            } else if (t1.equals(1)) {
                difficultyLevel.setText(bundle.getString("diffLevel02"));
                difficultyLevel.setStyle("-fx-text-fill: #e5a40d");
                difficulty = DifficultyLevel.Difficulty.MEDIUM;
                logger.info("Difficulty: medium was chosen");
            } else if (t1.equals(2)) {
                difficultyLevel.setText(bundle.getString("diffLevel03"));
                difficultyLevel.setStyle("-fx-text-fill: #bf0303");
                difficulty = DifficultyLevel.Difficulty.HARD;
                logger.info("Difficulty: hard was chosen");
            }
        })));

        langChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener((((observableValue, s, t1) -> {
            if (t1.equals(0)) {
                if (bundle.getLocale().equals(new Locale("en"))) {
                    try {
                        reload(new Locale("pl"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (t1.equals(1)) {
                if (bundle.getLocale().equals(new Locale("pl"))) {
                    try {
                        reload(new Locale("en"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        })));

    }

    public void startGame() throws FxmlException {
        logger.info("Game is starting");
        if (difficultyChoiceBox.getValue().equals("")) {
            logger.warn("Difficulty wasnt chosen");
            DialogBox.showMessage(bundle.getString("noDiffLevel"), Alert.AlertType.WARNING);
            logger.info("Returning to menu");
            return;
        }
        AnchorPane anchorPane = null;
        try{
            anchorPane = FXMLLoader.load(this.getClass()
                    .getResource("/fxml/sudokuWindow.fxml"), bundle);
        } catch(IOException e) {
            logger.error("Cant open sudokuWindow.fxml");
            throw new FxmlException("Cant open sudokuWindow.fxml", e);
        }
        SudokuWindowController.bundle = bundle;
        Stage stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                logger.info("Closing application");
                Platform.exit();
            }
        });

        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("/styles/boardStyle.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        mainAnchorPane.getScene().getWindow().hide();
    }

    public void reload(Locale loc) throws FxmlException {
        logger.info("Reloading with " + loc.toLanguageTag() + " language");
        bundle = ResourceBundle.getBundle("language", loc);
        AnchorPane anchorPane = null;
        try{
            anchorPane = FXMLLoader.load(this.getClass()
                    .getResource("/fxml/menuWindow.fxml"), bundle);
        } catch(IOException e) {
            logger.error("Cant reload menuWindow");
            throw new FxmlException("Cant reload menuWindow", e);
        }
        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                logger.info("Closing application");
                Platform.exit();
            }
        });
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("/styles/boardStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public void showAuthors() {
        logger.info("Opening author window");
        ResourceBundle authorsList =
                ResourceBundle.getBundle("pl.module.Authors", bundle.getLocale());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle((String)authorsList.getObject("title"));
        alert.setContentText(authorsList.getObject("author1")
                + "\n" + authorsList.getObject("author2"));
        alert.showAndWait();
        logger.info("Closing author window");
    }

    public static DifficultyLevel.Difficulty getDifficulty() {
        return difficulty;
    }


}
