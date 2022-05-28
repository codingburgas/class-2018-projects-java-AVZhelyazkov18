package services;

import main.App;
import repositories.UserRepository;
import repositories.models.User;
import utils.ConsoleUtils;
import utils.SQLUtils;

import java.sql.SQLException;

public class ActionService {


    public static void registerUser() {
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

    public static void loginUser() {
        String firstInput = ConsoleUtils.stringInput("Please enter your username: ");
        String secondInput = ConsoleUtils.stringInput("Please enter your password: ");

        try {
            User newUser = UserRepository.loginUser(firstInput, secondInput);

            if(newUser != null) {
                if(UserService.getCurrentLoggedInUser() != null)
                    UserService.getCurrentLoggedInUser().removeUser();
                UserService.setCurrentLoggedInUser(newUser);

                ConsoleUtils.writeConsoleLine("Welcome " + newUser.getUsername() + "!");
            } else {
                ConsoleUtils.writeConsoleLine("Couldn't fetch account data. Username or password might be wrong. Please try again.\n");
            }
        } catch (SQLException sqlException) {
            ConsoleUtils.writeConsoleLine("There was a problem with the server request. Please contact an administrator if the problem persists.");
            sqlException.printStackTrace();
        }
    }

    public static void logOut() {
        if(UserService.getCurrentLoggedInUser() != null)
            UserService.getCurrentLoggedInUser().removeUser();
        UserService.setCurrentLoggedInUser(null);
    }
}
