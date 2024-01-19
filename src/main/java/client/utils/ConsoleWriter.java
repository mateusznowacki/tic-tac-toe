package client.utils;

import java.util.ArrayList;

public class ConsoleWriter {
    public static void printPlayerAddInfo(String name, int id) {
        System.out.println(name + " - you joined the server, your id is: " + id);
    }

    public static void printMenu() {
        System.out.println("1. Play with other player");
        System.out.println("2. Observe other game");
        System.out.println("3. Exit");
    }

    public static void printGameBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println("  ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }

    }

    public static void printFieldNumbers() {
        System.out.println("");
        System.out.println("7  8  9");
        System.out.println("4  5  6");
        System.out.println("1  2  3");
        System.out.println("Choose field number: ");
    }



    public static void printRunningMatches(ArrayList<Integer> matchesId) {
        System.out.println("Choose one of running matches by typing its id:");
        for (Integer matchId : matchesId) {
            System.out.println("Match id: " + matchId);
        }
    }

}
