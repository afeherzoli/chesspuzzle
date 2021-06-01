package chesspuzzle.controller;


import chesspuzzle.jaxb.JAXBHelper;
import chesspuzzle.results.Game;
import chesspuzzle.results.GameResults;
import jakarta.xml.bind.JAXBException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.tinylog.Logger;
import chesspuzzle.model.ChessPuzzleModel;
import chesspuzzle.model.Piece;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameController {

    @FXML
    private GridPane board;

    @FXML
    private Label wonLabel;

    @FXML
    private Label stepsLabel;

    @FXML
    private Button submitButton;

    private ChessPuzzleModel model = new ChessPuzzleModel();

    private StringProperty playerName = new SimpleStringProperty();

    private IntegerProperty steps = new SimpleIntegerProperty();

    public void setPlayerName(String text) {
        this.playerName.set(text);
    }

    @FXML
    public void initialize() {
        Logger.debug("r:{} c:{}", board.getRowCount(), board.getColumnCount());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++){
                var txt = createTxt(i, j);
                board.add(txt, j, i);
                Logger.debug("ciklus:{},{} bábu:{}", i, j, txt);
            }
        }
        stepsLabel.textProperty().bind(Bindings.concat("Steps: ", steps));
        steps.set(0);
    }

    private Text createTxt(int i, int j) {
        var t = new Text(10,50,"");
        t.setFont(new Font(50));
        t.setWrappingWidth(200);
        t.setTextAlignment(TextAlignment.CENTER);
        t.textProperty().bind(new ObjectBinding<String>() {
            {
                super.bind(model.pieceProperty(i, j));
            }
            @Override
            protected String computeValue() {
                if (model.pieceProperty(i, j).get() == Piece.KING)
                    return "♔";
                if (model.pieceProperty(i, j).get() == Piece.BISHOP)
                    return "♗";
                if (model.pieceProperty(i, j).get() == Piece.ROOK)
                    return "♖";
                return "";
            };
        });
        t.setOnMouseClicked(this::clickOnPiece);
        return t;
    }

    public void clickOnPiece(MouseEvent mouseEvent) {
        var t = (Text) mouseEvent.getSource();
        var row = GridPane.getRowIndex(t);
        var col = GridPane.getColumnIndex(t);
        Logger.debug("Piece at {},{} pressed", row, col);
        if (model.canMove(row, col) && !model.isGameWon()){
            model.move(row, col);
            steps.set(steps.get()+1);
            if (model.isGameWon()){
                wonLabel.setText(String.format("%s won the game!", playerName.get()));
                submitButton.setDisable(false);
            }
        } else {
            Logger.debug("Piece at:{},{} cant move", row, col);
        }
    }

    public void showHighscores(ActionEvent actionEvent) throws IOException, JAXBException {
        var gameResults = JAXBHelper.fromXML(GameResults.class, new FileInputStream("data.xml"));
        Game game = new Game();
        game.setPlayer(playerName.get());
        game.setSteps(steps.get());
        //game.setTime(LocalDateTime.of(2020, 04, 12, 15, 22));

        gameResults.addGame(game);
        JAXBHelper.toXML(gameResults,new FileOutputStream("data.xml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/chesspuzzle/highscore.fxml"));
        Parent root = fxmlLoader.load();
        HighscoreController highscoreController = fxmlLoader.getController();
        highscoreController.setPlayerName(playerName.get());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        Logger.info("Showing highscores");
    }
}
