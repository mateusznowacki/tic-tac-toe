package client.utils;

import java.util.Scanner;

public class InputValidator {

    public static boolean validateArguments(String[] args) {
        boolean validArgumentsLength;
        boolean validName;

        validArgumentsLength = args.length == 1;

        validName = args[0] != null && args[0].length() > 0;

        return validArgumentsLength && validName;
    }

    public static int[] getValidNumber() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        int[] coordinates = new int[2];

        switch (input) {
            case "7":
                coordinates[0] = 0;
                coordinates[1] = 0;
                break;
            case "8":
                coordinates[0] = 0;
                coordinates[1] = 1;
                break;
            case "9":
                coordinates[0] = 0;
                coordinates[1] = 2;
                break;
            case "4":
                coordinates[0] = 1;
                coordinates[1] = 0;
                break;
            case "5":
                coordinates[0] = 1;
                coordinates[1] = 1;
                break;
            case "6":
                coordinates[0] = 1;
                coordinates[1] = 2;
                break;
            case "1":
                coordinates[0] = 2;
                coordinates[1] = 0;
                break;
            case "2":
                coordinates[0] = 2;
                coordinates[1] = 1;
                break;
            case "3":
                coordinates[0] = 2;
                coordinates[1] = 2;
                break;
        }
        return coordinates;
    }

    public static int chooseMatchId(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        return Integer.parseInt(input);
    }




}

