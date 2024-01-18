package client.app;

import client.model.Player;
import shared.TicTacToeService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import static client.utils.ConsoleWriter.printMenu;
import static client.utils.ConsoleWriter.printPlayerAddInfo;
import static client.utils.InputValidator.validateArguments;


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
                    }break;

                    case 2: {

                    }break;

                    case 3: {
                        System.exit(0);
                    }break;

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
                server.runMatch(player.getId(),player.getMatchId());

            } else if (gameOptions.get(0).equals("joinMatch")) {
                System.out.println(gameOptions.get(1));
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
