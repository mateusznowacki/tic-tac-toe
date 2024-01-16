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
    public TicTacToeGame() throws RemoteException {
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();

    }
    //TODO
//TODO

}
