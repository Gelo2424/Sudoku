package pl.module;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.module.exceptions.FxmlException;
import pl.module.exceptions.ReadFileException;
import pl.module.exceptions.WriteFileException;

public class SudokuWindowController {

    @FXML
    public GridPane gridPane;
    public AnchorPane mainAnchorPane;

    private static final Logger logger = LoggerFactory.getLogger(MenuWindowController.class.getName());
    private final FileChooser fileChooser = new FileChooser();
    private final SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    private static DifficultyLevel.Difficulty difficulty;
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardTemplate = new SudokuBoard(sudokuSolver);
    public static ResourceBundle bundle;


    public void initialize() throws CloneNotSupportedException, FxmlException {

        difficulty = MenuWindowController.getDifficulty();
        logger.info("Board with " + difficulty.toString() + " difficulty is loading");
        sudokuBoard.solveGame();
        sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        DifficultyLevel.prepareBoard(sudokuBoardCopy, difficulty);
        sudokuBoardTemplate = (SudokuBoard) sudokuBoardCopy.clone();
        fillBoard();
    }

    private void fillBoard() throws FxmlException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                NumberTextField textField = new NumberTextField();
                StringConverter converter = new IntegerStringConverter();

                try {
                    JavaBeanIntegerPropertyBuilder builder =
                            JavaBeanIntegerPropertyBuilder.create();

                    JavaBeanIntegerProperty integerProperty =
                            builder.bean(sudokuBoardCopy.getField(i, j)).name("fieldValue").build();

                    textField.textProperty().bindBidirectional(integerProperty, converter);

                } catch (NoSuchMethodException e) {
                    logger.error("Cant bind field (" + i + ", " + j + ")");
                    DialogBox.showMessage(bundle.getString("errorFillBoard"), Alert.AlertType.ERROR);
                    exit();
                }
                textField.setAlignment(Pos.CENTER);
                if (sudokuBoardTemplate.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setOpacity(1);
                    textField.setId("cell");
                    textField.setText(String.valueOf(sudokuBoardTemplate.get(i, j)));
                } else if (sudokuBoardCopy.get(i, j) != 0) {
                    textField.setText(String.valueOf(sudokuBoardCopy.get(i, j)));
                    textField.setDisable(false);
                } else {
                    textField.setText("");
                }
                gridPane.add(textField, j, i);
            }
        }
    }

    public void readSudokuBoard() throws FxmlException {
        logger.info("Reading sudoku from a file");
        fileChooser.setTitle(bundle.getString("chooseFile"));
        File file = fileChooser.showOpenDialog(null);
        SudokuBoard readBoard;
        SudokuBoard readTemplate;
        SudokuBoard readSolve;
        if (file == null) {
            logger.info("The file was not selected");
            return;
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString())) {
            readBoard = dao.read();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            DialogBox.showMessage(bundle.getString("errorLoad"), Alert.AlertType.WARNING);
            return;
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString() + "Template")) {
            readTemplate = dao.read();
        } catch (Exception e) {
            logger.warn(e.getMessage() + ". Cant find template");
            DialogBox.showMessage(bundle.getString("errorLoad"), Alert.AlertType.WARNING);
            return;
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString() + "Solve")) {
            readSolve = dao.read();
        } catch (Exception e) {
            logger.warn(e.getMessage() + ". Cant find solve");
            DialogBox.showMessage(bundle.getString("errorLoad"), Alert.AlertType.WARNING);
            return;
        }
        logger.info("Sudoku has been loaded");
        sudokuBoardCopy = readBoard;
        sudokuBoardTemplate = readTemplate;
        sudokuBoard = readSolve;

        fillBoard();
    }

    public void writeSudokuBoard() {
        logger.info("Writing sudoku to a file");
        fileChooser.setTitle(bundle.getString("writeFile"));
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            return;
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString())) {
            dao.write(sudokuBoardCopy);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            DialogBox.showMessage(bundle.getString("errorWrite"), Alert.AlertType.WARNING);
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString() + "Template")) {
            dao.write(sudokuBoardTemplate);
            //setHiddenAttrib(Paths.get(file.toString() + "Template"));
        } catch (Exception e) {
            logger.warn(e.getMessage() + ". Cant write template");
            DialogBox.showMessage(bundle.getString("errorWrite"), Alert.AlertType.WARNING);
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.toString() + "Solve")) {
            dao.write(sudokuBoard);
            //setHiddenAttrib(Paths.get(file.toString() + "Solve"));
        } catch (Exception e) {
            logger.warn(e.getMessage() + ". Cant write solve");
            DialogBox.showMessage(bundle.getString("errorWrite"), Alert.AlertType.WARNING);
        }
        logger.info("Sudoku has been written");
    }

//    private static void setHiddenAttrib(Path filePath) throws DaoException {
//        try {
//            DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
//            Files.setAttribute(filePath, "dos:hidden", true);
//        } catch (IOException e) {
//            throw new DaoException(e);
//        }
//    }

    public void checkBoard() {
        logger.info("Checking sudoku");
        //TODO

    }

    public void exit() throws FxmlException {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(this.getClass()
                            .getResource("/fxml/menuWindow.fxml"),
                    ResourceBundle.getBundle("language", bundle.getLocale()));
        } catch (IOException e) {
            logger.error("Cant load menuWindow.fxml");
            throw new FxmlException("Cant load FXML file", e);
        }
        Stage stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                logger.info("Closing application");
                Platform.exit();
            }
        });
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
        mainAnchorPane.getScene().getWindow().hide();
        logger.info("Returning to menu");
    }

}