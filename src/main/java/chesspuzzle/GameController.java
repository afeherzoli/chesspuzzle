package chesspuzzle;

import java.lang.Integer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.tinylog.Logger;
import chesspuzzle.model.ChessPuzzleModel;
import chesspuzzle.model.Piece;

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
    public void initialize(){
        Logger.debug("r:{} c:{}",board.getRowCount(),board.getColumnCount());
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++){//board.getColumnCount()
                //var square = new StackPane();
                //var txt = new Text("K");
                //square.getChildren().add(txt);
                var txt = createTxt(i, j);
                board.add(txt, j, i);
                Logger.debug("ciklus:{},{} bábu:{}",i,j, txt);
            }
        }
        stepsLabel.textProperty().bind(Bindings.concat("Steps: ", steps));
        steps.set(0);
    }

    private Text createTxt(int i, int j){
        var t = new Text(10,50,"");
        t.setFont(new Font(50));
        t.setWrappingWidth(200);
        t.setTextAlignment(TextAlignment.CENTER);
        //t.textProperty().bind(Bindings.convert(model.piecePropertyF(i,j)));
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
            if(model.isGameWon()){
                wonLabel.setText(String.format("%s won the game!", playerName.get()));
                submitButton.setDisable(false);
            }
        }
        else{
            Logger.debug("Piece at:{},{} cant move", row, col);
        }
    }
}
