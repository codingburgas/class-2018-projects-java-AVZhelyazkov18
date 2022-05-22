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
            } else if(chosenNumber == 2) {
                loginUser();
            } else if(chosenNumber == -1){
                ConsoleUtils.writeConsoleLine("Please Enter A Valid Option.");
                ConsoleUtils.writeConsoleLine("");
            }
        } while(chosenNumber != 0);

    }

    private static void registerUser() {
        String userName = ConsoleUtils.stringInput("Username: ");
        String userEmail = ConsoleUtils.stringInput("Email: ");
        String userPassword = ConsoleUtils.stringInput("Password: ");

        if(UserRepository.checkIfUserNameAlreadyExists(userName)) {
            ConsoleUtils.writeConsoleLine("The username has already been taken. Please write another username.");
        } else {
            boolean isCreated = UserRepository.addUserToUserTable(userName, userEmail, userPassword);
            if(isCreated)
                ConsoleUtils.writeConsoleLine("User has been saved.");
            else
                ConsoleUtils.writeConsoleLine("User could not be saved.");
        }
    }

    private static void loginUser() {

    }
}
