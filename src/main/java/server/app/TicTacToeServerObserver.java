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
import java.util.List;

public class TicTacToeServerObserver implements TicTacToeServiceObserver {

    // In the server's observeGame method
    @Override
    public void observeGame(TicTacToeService server) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            System.out.println("Observer server is running...");
            System.out.println("Observer connected");

            // Send available match IDs to the client
            printRunningMatches(server.getRunningMatches(), socket);

            int matchId = Integer.parseInt(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine());

            while (server.isMatchRunning(matchId)) {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                String gameInfo = buildGameInfo(server.getBoard(matchId), server.getCurrentPlayerName(matchId));
                if (server.isNextTurn(matchId)) {
                    String[] lines = gameInfo.split("\n");
                    for (String line : lines) {
                        output.println(line);
                    }
                }
            }

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to print available match IDs to the client
    private void printRunningMatches(List<Integer> runningMatches, Socket socket) throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Available match IDs: " + runningMatches);
        output.println("Choose match ID to observe:");
    }

    private String buildGameInfo(char[][] board, String currentPlayerName) {
        StringBuilder gameInfo = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                gameInfo.append(board[i][j]).append(" ");
            }
            gameInfo.append("\n");
        }
        gameInfo.append("Current player name: ").append(currentPlayerName).append("\n");
        return gameInfo.toString();
    }


}
