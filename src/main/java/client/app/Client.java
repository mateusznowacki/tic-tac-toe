package client.app;

import client.model.Player;
import shared.TicTacToeService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import static client.utils.ConsoleWriter.*;
import static client.utils.InputValidator.*;


public class Client {

    public static void main(String[] args) {


        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TicTacToeService server = (TicTacToeService) registry.lookup("TicTacToe");

            initializeClient(args, server);
            Player player = Player.getInstance();

            int input;
            Scanner scanner = new Scanner(System.in);
            while (true) {

                printMenu();
                input = Integer.parseInt(scanner.nextLine().trim());

                switch (input) {
                    case 1: {
                        joinGame(server, player);
                    }
                    break;

                    case 2: {

                    }
                    break;

                    case 3: {
                        System.exit(0);
                    }
                    break;

                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }


    private static void joinGame(TicTacToeService server, Player player) {
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

    private static void runGame(TicTacToeService server, Player player) {
        try {
            do {
                if (server.isMyTurn(player.getMatchId(), player.getId()) && server.isMatchReady(player.getMatchId())) {
                    makeMove(server, player);
                    if (checkMatchResult(server, player)) {
                        printWinnerInfo(player);
                    }
                }
            } while (server.isMatchRunning(player.getMatchId()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkMatchResult(TicTacToeService server, Player player) {
        try {
            if (server.isWinner(player.getMatchId(), player.getId())) {
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeMove(TicTacToeService server, Player player) throws RemoteException {
        System.out.println("It is your turn choose free field");
        printGameBoard(server.getBoard(player.getMatchId()));
        printFieldNumbers();

        server.makeMove(player.getMatchId(), player.getId(), getValidNumber());
    }

    private static void initializeClient(String[] args, TicTacToeService server) throws RemoteException {
        String name = validateArguments(args) ? args[0] : "RandomPlayer";
        Player player = Player.getInstance();

        int clientId = server.registerPlayer(name);

        player.setName(name);
        player.setId(clientId);

        printPlayerAddInfo(name, player.getId());
    }
}
