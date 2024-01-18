package server.app;


import shared.TicTacToeService;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static server.io.ConsolePrinter.printAppCrashInfo;
import static server.io.ConsolePrinter.printStartInfo;


public class Server {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            InetAddress serverAddress = InetAddress.getByName("localhost");

            TicTacToeService game = new TicTacToeServer();
            Naming.rebind("TicTacToe", game);
            printStartInfo(serverAddress.getHostAddress());


        } catch (Exception e) {
            printAppCrashInfo(e);
            System.exit(1);
        }
    }

}
