package client;

import static client.InputValidator.validateArguments;

public class Client {
    public static void main(String[] args) {

        if(validateArguments(args)){
            String name = args[0];

        } else {
            System.out.println("Invalid arguments. Your name is now ABC!");
            String name = "ABC";
        }





    }
}
