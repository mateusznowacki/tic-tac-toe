package server.io;

public class ConsolePrinter {
    public static void printStartInfo(String address) {

        System.out.println("Host adress: " + address);
        System.out.println("RMI Registry ready!");
        System.out.println("TicTacToe server ready!");

    }

    public static void printAppCrashInfo(Exception e){
        System.err.println("TicTacToe server failed!");
        System.err.println(e.getMessage());
    }
}
