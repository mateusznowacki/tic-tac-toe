package server.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeService extends Remote {
    void registerPlayer(String name , int id) throws RemoteException;
    void deletePlayer (int id) throws RemoteException;

    void makeMove (int matchId,int playerId, int row, int column) throws RemoteException;
}
