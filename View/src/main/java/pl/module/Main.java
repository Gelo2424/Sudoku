package pl.module;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        logger.info("Aplication is starting");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(new Locale("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("language");
        MenuWindowController.bundle = bundle;
        AnchorPane anchorPane = FXMLLoader.load(this.getClass()
                .getResource("/fxml/menuWindow.fxml"), bundle);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("title"));
        stage.setResizable(false);
        stage.show();
    }

}
