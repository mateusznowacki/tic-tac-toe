package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TicTacToeService extends Remote {

    void joinRunningMatch(int playerId, int matchId) throws RemoteException;

    ArrayList<String> getGameOptions() throws RemoteException;

    void makeMove(int matchId, int playerId, int [] coordinates) throws RemoteException;


    int registerPlayer(String name) throws RemoteException;


    void createNewMatch(int playerId) throws RemoteException;

    int getMatchId(int playerId) throws RemoteException;

    boolean isAnyWinner(int matchId) throws RemoteException;


    boolean isMatchRunning(int matchId) throws RemoteException;

    boolean isMyTurn(int matchId, int id) throws RemoteException;

    char[][] getBoard(int matchId) throws RemoteException;

    boolean isMatchReady(int matchId) throws RemoteException;
    ArrayList<Integer> getRunningMatches() throws RemoteException;

    int getWinner(int matchId) throws RemoteException;

    String getCurrentPlayerName(int matchId) throws RemoteException;

    boolean isNextTurn()throws RemoteException;
}
