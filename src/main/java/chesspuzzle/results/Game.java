package chesspuzzle.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Game {

    private String player;
    private int steps;
    //private LocalDateTime time;

    public Game() {}
    public Game(String player, int steps/*, LocalDateTime time*/){
        super();
        this.player = player;
        this.steps = steps;
        //this.time = time;
    }

    //@XmlElement(name = "player")
    public String getPlayer(){
        return player;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    //@XmlElement(name = "steps")
    public int getSteps(){
        return steps;
    }

    public void setSteps(int steps){
        this.steps = steps;
    }

    /*@XmlElement(name = "time")
    public LocalDateTime getTime(){
        return time;
    }

    public void setTime(LocalDateTime time){
        this.time = time;
    }*/
}
