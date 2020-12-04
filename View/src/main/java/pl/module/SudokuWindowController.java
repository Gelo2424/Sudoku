package pl.module;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class SudokuWindowController {
    public GridPane gridPane;
    public AnchorPane mainAnchorPane;

    public void initialize() {
        System.out.println(MenuWindowController.getDifficulty());
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                TextField textField = new TextField();

                gridPane.add()
            }
        }
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

    public void checkBoard() {

        //TODO

    }
}
