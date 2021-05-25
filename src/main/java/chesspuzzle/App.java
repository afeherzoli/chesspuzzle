package chesspuzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.inject.Inject;
import java.io.IOException;

/**
 * JavaFX App

public class App extends Application {

    private static Scene scene;

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        Logger.info("Starting App");
        //fxmlLoader.setLocation(getClass().getResource("start.fxml"));
        //Parent root = fxmlLoader.load();
        //stage.setTitle("Chess Puzzle");
        scene = new Scene(loadFXML("start"), 640, 480);
        //stage.setScene(new Scene(root));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}*/

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/chesspuzzle/start.fxml"));
        stage.setTitle("Chess puzzle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}