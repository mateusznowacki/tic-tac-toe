package server.engine;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class GameStats {
    private static GameStats instance;

    private HashMap<Integer, Integer> playerWins = new HashMap<>();
    private HashMap<Integer, Integer> playerLosses = new HashMap<>();
    private HashMap<Integer, Integer> playerDraws = new HashMap<>();


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
