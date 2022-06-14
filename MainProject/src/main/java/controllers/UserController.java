package controllers;

import services.TopicService;
import services.UserService;
import utils.ConsoleUtils;
import static services.UserService.*;

/*
    Layer #1: Data Presentation
*/
public class UserController {

    private UserService userService;

    /*
      Dependency injection pattern, see:
      https://www.vogella.com/tutorials/DependencyInjection/article.html#:~:text=Dependency%20injection%20(DI)%20is%20the,an%20instance%20of%20this%20class
      In short, dependency injection is a pattern which is used in order to simplify management of state/dependencies
      In this case, "SampleService" is being injected into "SampleController" as a dependency, and "SampleController"
      manages state of the "SampleService".
    */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public static void askUserAction() {
        int chosenOption = 0;
        do {
            if (UserService.getCurrentLoggedInUser() != null) {
                registeredUserMessage();
            } else {
                nonRegisteredUserMessage();
            }

            chosenOption = ConsoleUtils.readConsoleInt();

            if (UserService.getCurrentLoggedInUser() != null) {
                ifUserRegistered(chosenOption);
            } else {
                ifUserNotRegistered(chosenOption);
            }

        } while (chosenOption != 0);
    }

    public static void welcomeMessage() {
        ConsoleUtils.writeConsoleLine("NOTE: This is just a CLI Application which intends to show a replica of the functions on how the website would work in the future.");
        ConsoleUtils.writeConsoleLine("Everything shown in the application would be directly applied onto the website that is worked on and will be done in the future.");
    }

    private static void nonRegisteredUserMessage() {
        ConsoleUtils.writeConsoleLine("Would you like to register or login?");
        ConsoleUtils.writeConsoleLine("1. Register");
        ConsoleUtils.writeConsoleLine("2. Login");
        ConsoleUtils.writeConsoleLine("0. Exit");
    }

    private static void registeredUserMessage() {
        ConsoleUtils.writeConsoleLine("Please choose an option.");
        ConsoleUtils.writeConsoleLine("1. Check Forums");
        ConsoleUtils.writeConsoleLine("2. Log Out");
        ConsoleUtils.writeConsoleLine("0. Exit");
    }

    private static void ifUserRegistered(Integer chosenOption) {
        if (chosenOption == 1) {
            TopicController.askUserTopicAction();
        } else if (chosenOption == 2) {
            logOut();
        } else if (chosenOption == -1) {
            ConsoleUtils.writeConsoleLine("Please Enter A Valid Option.");
        }
    }

    private static void ifUserNotRegistered(Integer chosenOption) {
        if (chosenOption == 1)
            registerUser();
        else if (chosenOption == 2)
            loginUser();
        else if (chosenOption == -1)
            ConsoleUtils.writeConsoleLine("Please Enter A Valid Option.");
    }
}
