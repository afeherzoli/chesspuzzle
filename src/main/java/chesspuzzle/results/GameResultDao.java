package chesspuzzle.results;

import chesspuzzle.jaxb.JAXBHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import jakarta.xml.bind.JAXBException;
import org.tinylog.Logger;

public class GameResultDao {
/*
    public void save(GameResults gameResults) throws JAXBException {
        try {
            JAXBHelper.toXML(gameResults, new FileOutputStream("/chesspuzzle/results/gameResults.xml"));
            Logger.debug(JAXBHelper.fromXML(GameResults.class, new FileInputStream("/chesspuzzle/results/gameResults.xml")));
        }
        catch (FileNotFoundException | JAXBException e) {
            Logger.debug(e);
        }
    }

    public List<Game> getGames() throws FileNotFoundException, JAXBException {
        try {
            var gameList = JAXBHelper.fromXML(GameResults.class, new FileInputStream("/chesspuzzle/results/gameResults.xml")).getGames();
            return gameList;
        } catch (FileNotFoundException | JAXBException e){
            Logger.debug(e);
        }
        return null;
    }*/
}
