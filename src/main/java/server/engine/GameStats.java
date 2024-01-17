package server.engine;

import lombok.Getter;
import lombok.Setter;
import server.model.Player;

import java.util.ArrayList;

@Setter
@Getter
public class GameStats {
    private static GameStats instance;

    ArrayList<Match> matches = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    private GameStats() {
    }

    public static GameStats getInstance() {
        if (instance == null) {
            synchronized (GameStats.class) {
                if (instance == null) {
                    instance = new GameStats();
                }
            }
        }
        return instance;
    }

}
