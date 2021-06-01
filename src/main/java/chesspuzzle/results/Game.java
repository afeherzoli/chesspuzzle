package chesspuzzle.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.*;

/**
 * Class that represent one game played by a specific player.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Game implements Comparable<Game>{

    /**
     * The name of the player;
     */
    private String player;
    /**
     * The number of steps took the player to solve the puzzle.
     */
    private int steps;

    public Game() {}
    public Game(String player, int steps){
        super();
        this.player = player;
        this.steps = steps;
    }

    public String getPlayer(){
        return player;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public int getSteps(){
        return steps;
    }

    public void setSteps(int steps){
        this.steps = steps;
    }

    @Override
    public int compareTo(Game o) {
         if(this.steps == o.steps) return 0;
         else if(this.steps > o.steps) return 1;
         else return -1;
    }
}
