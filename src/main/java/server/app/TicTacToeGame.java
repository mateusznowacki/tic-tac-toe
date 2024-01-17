package server.app;

import server.engine.Match;
import server.model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame extends UnicastRemoteObject implements TicTacToeService {
    private List<Player> players;
    private List<Match> matches;
    private int playerId = 0;
    public TicTacToeGame() throws RemoteException {
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    @Override
    public void registerPlayer(String name, int id) throws RemoteException {
        players.add(new Player(id, name));
    }

    @Override
    public void deletePlayer(int id) throws RemoteException {
        players.removeIf(player -> player.getId() == id);

    }

    @Override
    public void makeMove(int matchId,int playerId, int row, int column) throws RemoteException {
       matches.get(matchId).makeMove(row,column, playerId);

    }
}
