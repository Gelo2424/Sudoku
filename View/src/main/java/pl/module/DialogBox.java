package pl.module;

import javafx.scene.control.Alert;

public class DialogBox {

    public static void showMessage(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("An error occured");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
