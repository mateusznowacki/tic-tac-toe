package server.app;

import shared.TicTacToeService;
import shared.TicTacToeServiceObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class TicTacToeServerObserver implements TicTacToeServiceObserver {

    @Override
    public void observeGame(TicTacToeService server) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            System.out.println("Observer server is running...");
            System.out.println("Observer connected");

            int matchId = Integer.parseInt(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine());

            do {

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                while (server.isMatchRunning(matchId)) {
                    String gameInfo = getGameInfo(server, matchId);
                    output.println(gameInfo);
                }

                output.close();
                socket.close();
            } while (server.isNextTurn());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGameInfo(TicTacToeService server, int matchId) {
        try {
            StringBuilder gameInfo = new StringBuilder();
            char[][] board = server.getBoard(matchId);

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    gameInfo.append(board[i][j]).append(" ");
                }
                gameInfo.append("\n");
            }
            gameInfo.append("Current player name: ").append(server.getCurrentPlayerName(matchId)).append("\n");

            return gameInfo.toString();
        } catch (
                RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
