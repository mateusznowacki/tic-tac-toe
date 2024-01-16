package server.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeService extends Remote {
    int addPlayer(String name) throws RemoteException;
    int endMatch (int id) throws RemoteException;
    int hasMatch (int id) throws RemoteException;
    String getOpponent(int id) throws RemoteException;
    int isMyTurn(int id) throws RemoteException;
    String getBoard(int id) throws RemoteException;
    int move (int playerId, int row, int column) throws RemoteException;
}
