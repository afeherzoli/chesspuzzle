package chesspuzzle.controller;

import chesspuzzle.jaxb.JAXBHelper;
import chesspuzzle.results.Game;
import chesspuzzle.results.GameResultDao;
import chesspuzzle.results.GameResults;
import jakarta.xml.bind.JAXBException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.*;
import java.time.LocalDateTime;

public class HighscoreController {
    @FXML
    private TableView<Game> highscoreTable;

    @FXML
    private TableColumn<GameResults, Integer> steps;

    @FXML
    private TableColumn<GameResults, String> player;

    @FXML
    private TableColumn<GameResults, LocalDateTime> time;

    private StringProperty playerName = new SimpleStringProperty();

    private GameResultDao gameResultDao;

    public void setPlayerName(String text) {
        this.playerName.set(text);
    }

    @FXML
    private void initialize() throws FileNotFoundException, JAXBException {
        Logger.debug("Loading highscores");
        var gameResults = JAXBHelper.fromXML(GameResults.class, new FileInputStream("data.xml"));

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        //player.setCellValueFactory(new PropertyValueFactory<>("time"));

        ObservableList<Game> observableList = FXCollections.observableArrayList();
        observableList.addAll(gameResults.getGames());

        highscoreTable.setItems(observableList);
    }

    public void handleNewGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/chesspuzzle/game.fxml"));
        Parent root = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        gameController.setPlayerName(playerName.get());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        Logger.info("{} started a new game", playerName.get());
    }
}
