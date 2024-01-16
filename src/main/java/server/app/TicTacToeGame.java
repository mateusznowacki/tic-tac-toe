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
    public int addPlayer(String name) throws RemoteException {
        return 0;
    }

    @Override
    public int endMatch(int id) throws RemoteException {
        return 0;
    }

    @Override
    public int hasMatch(int id) throws RemoteException {
        return 0;
    }

    @Override
    public String getOpponent(int id) throws RemoteException {
        return null;
    }

    @Override
    public int isMyTurn(int id) throws RemoteException {
        return 0;
    }

    @Override
    public String getBoard(int id) throws RemoteException {
        return null;
    }

    @Override
    public int move(int playerId, int row, int column) throws RemoteException {
        return 0;
    }
}
