package shared;

import server.engine.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TicTacToeService extends Remote {

    void playWithOtherPlayer(int playerId, int matchId) throws RemoteException;

    ArrayList<String> getGameOptions() throws RemoteException;

    void makeMove(int matchId, int playerId, int row, int column) throws RemoteException;

    Match getMatch(int matchId) throws RemoteException;

    int registerPlayer(String name) throws RemoteException;

    String testConnection() throws RemoteException;

    void createNewMatch(int playerId) throws RemoteException;
    int getMatchId(int playerId) throws RemoteException;

    void runMatch(int id, int matchId);
}
