package client;

public class InputValidator {

    public static boolean validateArguments(String[] args) {
        boolean validArgumentsLength;
        boolean validName;

        if(args.length == 1){
            validArgumentsLength = true;
        } else {
            validArgumentsLength = false;
        }

        if(args[0] != null && args[0].length() > 0){
            validName = true;
        } else {
            validName = false;
        }

        return validArgumentsLength && validName;
    }

    public static boolean validateMove(int move) {
        return move >= 1 && move <= 9;
    }


}
