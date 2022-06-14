package services;

import java.sql.SQLException;
import repositories.UserRepository;
import repositories.models.User;
import utils.ConsoleUtils;

/*
    Layer #2: Business Logic
*/
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //public String getUsersWithLongUsername() {
        // 1. Get all users
        //List<User> users = userRepository.getUsers();
        // 2. Filter only those users that have username length bigger than 5 characters
        //users = users.stream().filter(user -> user.getUsername().length() > 5).collect(Collectors.toList());
        // 3. Convert the result to string representation and return it
        //return users.toString();
    //}

    private static User currentLoggedUser = null;

    public static User getCurrentLoggedInUser() { return currentLoggedUser; }
    public static void setCurrentLoggedInUser(User newUser) { currentLoggedUser = newUser; }

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



