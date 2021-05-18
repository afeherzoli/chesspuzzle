package chesspuzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tinylog.Logger;
import javafx.fxml.FXML;

//import javax.inject.Inject;
import java.io.IOException;

public class StartController {

    //@Inject
    //private FXMLLoader fxmlLoader;

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
            //fxmlLoader.setLocation(getClass().getResource("/game.fxml"));
            //Parent root = fxmlLoader.load();
            //fxmlLoader.<GameController>getController().setPlayerName(nameTextField.getText());
            //Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //stage.setScene(new Scene(root));
            //stage.show();
            App.setRoot("game");
            Logger.info("{} started a game",nameTextField.getText());
        }
    }
}
