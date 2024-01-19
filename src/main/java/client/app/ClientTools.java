package client.app;

import client.model.Player;
import shared.TicTacToeService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static client.utils.ConsoleWriter.*;
import static client.utils.InputValidator.*;

public class ClientTools {

    protected void joinGame(TicTacToeService server, Player player) {
        try {
            ArrayList<String> gameOptions = server.getGameOptions();
            if (gameOptions.get(0).equals("newMatch")) {
                System.out.println(gameOptions.get(1));
                server.createNewMatch(player.getId());
                player.setMatchId(server.getMatchId(player.getId()));
                runGame(server, player);

            } else if (gameOptions.get(0).equals("joinMatch")) {
                System.out.println(gameOptions.get(1));
                printRunningMatches(server.getRunningMatches());
                int matchId = chooseMatchId();
                server.joinRunningMatch(player.getId(), matchId);
                player.setMatchId(matchId);
                runGame(server, player);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void runGame(TicTacToeService server, Player player) {
        try {
            int result = 0;
            do {
                if (server.isMyTurn(player.getMatchId(), player.getId()) && server.isMatchReady(player.getMatchId())) {
                    if (checkAnyWinner(server, player)) {
                        result = server.getWinner(player.getMatchId());
                        if (result == player.getId()) {
                            System.out.println("You won the match");
                            System.out.println("You have " + player.getWins() + " wins");
                            break;
                        } else if (result != -1 && result != player.getId() && result != -9999) {
                            System.out.println("You lost the match");
                            break;
                        } else if (result != -1) {
                            System.out.println("It is a draw");
                            break;
                        } else if (result != -9999) {

                        }
                    }
                    makeMove(server, player);
                }
            } while (server.isMatchRunning(player.getMatchId()));
            if (result == player.getId()) {
                System.out.println("You won the match");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected boolean checkAnyWinner(TicTacToeService server, Player player) {
        try {
            if (server.isAnyWinner(player.getMatchId())) {
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    protected void makeMove(TicTacToeService server, Player player) throws RemoteException {
        System.out.println();
        System.out.println("It is your turn choose free field");
        printGameBoard(server.getBoard(player.getMatchId()));
        printFieldNumbers();

        server.makeMove(player.getMatchId(), player.getId(), getValidNumber());
    }

    protected void initializeClient(String[] args, TicTacToeService server) throws RemoteException {
        String name = validateArguments(args) ? args[0] : "RandomPlayer";
        Player player = Player.getInstance();

        int clientId = server.registerPlayer(name);

        player.setName(name);
        player.setId(clientId);

        printPlayerAddInfo(name, player.getId());
    }

    public void observeGame(TicTacToeService server, Player player) {
    }
}
