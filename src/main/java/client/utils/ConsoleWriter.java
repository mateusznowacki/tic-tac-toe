package client.utils;

public class ConsoleWriter {
    public static void printPlayerAddInfo(String name, int id) {
        System.out.println(name + " - you joined the server, your id is: " + id);
    }

    public static void printMenu() {
        System.out.println("1. Play with other player");
        System.out.println("2. Observe other game");
        System.out.println("3. Exit");
    }



}
