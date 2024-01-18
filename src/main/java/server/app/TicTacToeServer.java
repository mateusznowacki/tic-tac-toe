package server.app;

import server.engine.Match;
import server.model.Player;
import shared.TicTacToeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import static server.io.ConsolePrinter.printPlayerAddInfo;
//todo dodwanie gracza do meczu

public class TicTacToeServer extends UnicastRemoteObject implements TicTacToeService {
    private List<Player> players;
    private List<Match> matches;
    private int playerId = 0;
    private int matchId = 0;


    @Override
    public void runMatch(int id, int matchId) {

    }

    @Override
    public void playWithOtherPlayer(int id, int matchId) throws RemoteException {

    }

    @Override
    public void makeMove(int matchId, int playerId, int row, int column) throws RemoteException {
        matches.get(matchId).makeMove(row, column, playerId);
    }

    @Override
    public Match getMatch(int matchId) throws RemoteException {
        return matches.get(matchId);
    }

    @Override
    public int getMatchId(int playerId) throws RemoteException {
        for (Match match : matches) {
            if (match.getPlayer1().getId() == playerId || match.getPlayer2().getId() == playerId) {
                return match.getMatchId();
            }
        }
        return -1;
    }


    @Override
    public void createNewMatch(int id) {
        int matchId = this.matchId++;
        matches.add(new Match(matchId));
        matches.get(matchId).addPlayer(players.get(id));
    }


    @Override
    public ArrayList<String> getGameOptions() throws RemoteException {
        ArrayList<String> gameOptions = new ArrayList<>();
        if (matches.size() < 1) {
            gameOptions.add("newMatch");
            gameOptions.add("Brak dostępnych meczy, zostanie swtorzony nowy");
        } else if (matches.size() > 0) {
            for (Match match : matches) {
                if (match.isRunning() && match.getPlayerNumber() < 2) {
                    gameOptions.add("joinMatch");
                    gameOptions.add("Możesz dołączyć do meczu który już trwa");
                }
            }
        }
        return gameOptions;
    }

    @Override
    public int registerPlayer(String name) throws RemoteException {
        int playerId = this.playerId++;
        players.add(new Player(playerId, name));
        printPlayerAddInfo(name, playerId);

        return playerId;
    }

    @Override
    public String testConnection() throws RemoteException {
        System.out.println("Połączenie z serwerem udane!");
        return "Połączenie z serwerem udane!";
    }

    public TicTacToeServer() throws RemoteException {
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }
}
