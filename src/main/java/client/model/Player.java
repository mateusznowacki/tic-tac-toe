package client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private static Player instance;
    private int id;
    private String name;
    private int matchId;
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;
    private boolean isWinner = false;

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public void addDraw() {
        draws++;
    }

    private Player() {

    }

    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
}
