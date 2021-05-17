package chesspuzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.tinylog.Logger;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartController {

    @FXML
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField nameTextField;

    @FXML
    private Text errorLabel;

    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        if(nameTextField.getText().isEmpty()){
            errorLabel.setText("A name must be given!");
        }
        else {
            App.setRoot("game");
            Logger.info("Logged in!");
        }
    }
}
