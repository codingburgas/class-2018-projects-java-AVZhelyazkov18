package services;

import repositories.UserRepository;
import utils.ConsoleUtils;

public class ActionService {
    public static void askUserAction() {
        int chosenNumber = -1;

        do {
            ConsoleUtils.writeConsoleLine("| Client Side |");
            ConsoleUtils.writeConsoleLine("Would you like to register or login?");
            ConsoleUtils.writeConsoleLine("1. Register");
            ConsoleUtils.writeConsoleLine("2. Login");
            ConsoleUtils.writeConsoleLine("0. Exit");

            chosenNumber = ConsoleUtils.readConsoleInt();

            if(chosenNumber == 1) {
                registerUser();
            } else {
                loginUser();
            }
        } while(chosenNumber != 0);

    }

    public static void registerUser() {
        ConsoleUtils.clearConsole();

        String userName = ConsoleUtils.stringInput("Username: ");
        String userEmail = ConsoleUtils.stringInput("Email: ");
        String userPassword = ConsoleUtils.stringInput("Password: ");

        UserRepository.checkIfUserNameAlreadyExists(userName);
    }

    public static void loginUser() {

    }
}
