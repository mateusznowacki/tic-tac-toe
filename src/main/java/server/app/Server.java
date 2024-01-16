package server.app;

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
            printStartInfo(serverAddress.getHostAddress());

            TicTacToeGame game = new TicTacToeGame();
            Naming.rebind("TicTacToe", game);

        } catch (Exception e) {
            printAppCrashInfo(e);
            System.exit(1);
        }
    }




}
