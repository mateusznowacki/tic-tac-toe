package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeService extends Remote {
    int joinGame(String playerName) throws RemoteException;

    // Metoda do wykonania ruchu przez gracza
    int makeMove(int gameId, int row, int column, String playerName) throws RemoteException;

    // Metoda do pobrania aktualnego stanu planszy
    String getBoard(int gameId) throws RemoteException;

    // Metoda do sprawdzenia, czyja kolejka do ruchu
    String getCurrentPlayer(int gameId) throws RemoteException;

    // Metoda do sprawdzenia statusu gry (wygrana, remis, czyja kolejka)
    String getGameStatus(int gameId) throws RemoteException;

    // Metoda do opuszczenia gry
    void leaveGame(int gameId, String playerName) throws RemoteException;
}

