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
    private final List<Player> players;
    private final List<Match> matches;
    private int playerId = 0;
    private int matchId = 0;


    @Override
    public boolean isMatchReady(int matchId) {
        if (matches.get(matchId).getPlayerNumber() == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void playWithOtherPlayer(int id, int matchId) throws RemoteException {

    }


    // todo dodac sprawdzanie czy gracz jest w meczu
    @Override
    public void makeMove(int matchId, int playerId, int row, int column) throws RemoteException {
        matches.get(matchId).makeMove(row, column, playerId);
    }

    @Override
    public boolean isMyTurn(int matchId, int id) {
        return matches.get(matchId).getCurrentPlayer().getId() == id;
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
    public char[][] getBoard(int matchId) throws RemoteException {
        return matches.get(matchId).getBoard();
    }

    @Override
    public boolean isMatchRunning(int matchId) {
        return matches.get(matchId).isRunning();
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

    public TicTacToeServer() throws RemoteException {
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }
}
