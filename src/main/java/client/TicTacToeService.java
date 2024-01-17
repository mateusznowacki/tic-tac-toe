package client;

import server.engine.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeService extends Remote {
    void registerPlayer(String name ) throws RemoteException;
    void deletePlayer (int id) throws RemoteException;

    void makeMove (int matchId,int playerId, int row, int column) throws RemoteException;

    void createMatch (Player player1, Player player2) throws RemoteException;
    void deleteMatch (int matchId) throws RemoteException;

    Match getMatch (int matchId) throws RemoteException;
}

