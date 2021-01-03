package pl.module;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.module.exceptions.FxmlException;

public class Main extends Application {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        logger.info("Aplication is starting");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FxmlException {
        Locale.setDefault(new Locale("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("language");
        MenuWindowController.bundle = bundle;
        AnchorPane anchorPane = null;
        try{
            anchorPane = FXMLLoader.load(this.getClass()
                    .getResource("/fxml/menuWindow.fxml"), bundle);
        } catch(IOException e) {
            logger.error("Cant open menuWindow.fxml");
            throw new FxmlException("Cant open menuWindow.fxml", e);
        }

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                logger.info("Closing application");
                Platform.exit();
            }
        });

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("title"));
        stage.setResizable(false);
        stage.show();
    }

}
