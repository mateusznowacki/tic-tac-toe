package server.io;

import server.model.Player;

public class ConsolePrinter {
    public static void printStartInfo(String address) {
        System.out.println("Host adress: " + address);
        System.out.println("RMI Registry ready!");
        System.out.println("TicTacToe server ready!");
    }

    public static void printAppCrashInfo(Exception e) {
        System.err.println("TicTacToe server failed!");
        System.err.println(e.getMessage());
    }

    public static void printMatchInfo(String player1, String player2) {
        System.out.println("Match started between " + player1 + " and " + player2);
    }

    public static void printMatchEndInfo(String player1, String player2) {
        System.out.println("Match ended between " + player1 + " and " + player2);
    }

    public static void printPairingInfo(String player1, String player2) {
        System.out.println("Pairing between " + player1 + " and " + player2);
    }

    public static void printPlayerInfo(String name) {
        System.out.println("Player " + name + " joined the server");
    }

    public static void printWinInfo(Player player1, Player player2) {
        System.out.println("Player " + player1.getName() + " won the match against " + player2.getName());
        System.out.println("Player " + player1.getName() + " has " + player1.getWins() + " wins");
    }

    public static void printMatchDraw() {
        System.out.println("Match ended with a draw");
    }

}
