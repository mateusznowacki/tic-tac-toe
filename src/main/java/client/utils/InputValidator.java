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

    public static String getValidNumber(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        switch (input) {
            case "7":
                return "0 0";
            case "8":
                return "0 1";
            case "9":
                return "0 2";
            case "4":
                return "1 0";
            case "5":
                return "1 1";
            case "6":
                return "1 2";
            case "1":
                return "2 0";
            case "2":
                return "2 1";
            case "3":
                return "2 2";
            default:
                return "Invalid input";
        }
    }
}
