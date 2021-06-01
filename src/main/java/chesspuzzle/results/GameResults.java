package chesspuzzle.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "gameResults")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Setter
@Getter
public class GameResults {
    private List<Game> games;

    public GameResults() {}

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> gamez) {
        this.games = gamez;
    }

    public void addGame(Game game){
        this.games.add(game);
        Collections.sort(this.games);
    }

    public String toString(){
        return "gameResults:\n"+games.toString();
    }

}
