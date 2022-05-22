package controllers;

import services.UserService;
import utils.ConsoleUtils;

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

    public void displayUsersWithLongUsernames() {
        // 1. Get users with long usernames
        String someDataFromDatabase = userService.getUsersWithLongUsername();
        // 2. Display the list of users in console
        ConsoleUtils.writeConsoleLine("Displaying data from database: " + someDataFromDatabase);

        // 3. Ask User for console input
        ConsoleUtils.writeConsoleLine("Write your name:");
        final String name = ConsoleUtils.readConsoleLine();
        ConsoleUtils.writeConsoleLine("Your name is:" + name);
    }
}
