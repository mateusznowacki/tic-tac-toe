package client.app;

import client.model.Player;

import shared.TicTacToeService;
import shared.TicTacToeServiceObserver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;

import static client.utils.ConsoleWriter.*;



public class Client {


    public static void main(String[] args) {
        ClientTools clientTools = new ClientTools();

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TicTacToeService server = (TicTacToeService) registry.lookup("TicTacToe");
         //   TicTacToeServiceObserver serwerTCP = new TicTacToeServerObserver();

            clientTools.initializeClient(args, server);
            Player player = Player.getInstance();

            int input;
            Scanner scanner = new Scanner(System.in);
            while (true) {

                printMenu();
                input = Integer.parseInt(scanner.nextLine().trim());

                switch (input) {
                    case 1: {
                        clientTools.joinGame(server, player);
                    }
                    break;

                    case 2: {
                        clientTools.observeGame(server);
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

}
