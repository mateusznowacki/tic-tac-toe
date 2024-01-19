package server.app;


import shared.TicTacToeService;
import shared.TicTacToeServiceObserver;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static server.io.ConsolePrinter.printAppCrashInfo;
import static server.io.ConsolePrinter.printStartInfo;


public class Server {

    public static void main(String[] args) {

        try {
            //RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            InetAddress serverAddress = InetAddress.getByName("localhost");
            TicTacToeService game = new TicTacToeServer();
            Naming.rebind("TicTacToe", game);
            printStartInfo(serverAddress.getHostAddress());


            TicTacToeServiceObserver observer = new TicTacToeServerObserver();
            observer.observeGame(game);

        } catch (Exception e) {
            printAppCrashInfo(e);
            System.exit(1);
        }
    }

}
