package controllers;

import repositories.UserRepository;
import services.TopicService;
import services.UserService;
import utils.ConsoleUtils;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static services.UserService.*;

/*
    Layer #1: Data Presentation
*/
public class UserController {

    private UserService userService;

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
        ConsoleUtils.writeConsoleLine("2. User Settings");
        ConsoleUtils.writeConsoleLine("3. Log Out");
        ConsoleUtils.writeConsoleLine("0. Exit");
    }

    private static void ifUserRegistered(Integer chosenOption) {
        if (chosenOption == 1)
            TopicController.askUserTopicAction();
        else if(chosenOption == 2)
            UserController.userSettingsMenu();
        else if (chosenOption == 3)
            logOut();
        else if (chosenOption == -1)
            ConsoleUtils.writeConsoleLine("Please Enter A Valid Option.");
    }

    private static void ifUserNotRegistered(Integer chosenOption) {
        if (chosenOption == 1)
            registerUser();
        else if (chosenOption == 2)
            loginUser();
        else if (chosenOption == -1)
            ConsoleUtils.writeConsoleLine("Please Enter A Valid Option.");
    }

    public static void userSettingsDOBChange() {
        ConsoleUtils.writeConsoleLine("Format: <DD/MM/YYYY>");
        ConsoleUtils.writeConsoleLine("Enter New Date Of Birth: ");
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(ConsoleUtils.readConsoleNext());
            System.out.println(startDate.toString());
            LocalDate ld = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

            UserRepository.setUserData(null, java.sql.Date.valueOf(ld));
        } catch (ParseException e) {
            ConsoleUtils.writeConsoleLine("Wrong Date Format, please try again.");
        }
    }

    public static void userSettingsPNChange() {
        ConsoleUtils.writeConsoleLine("Enter New Phone Number: ");
        String phoneNumber = ConsoleUtils.readConsoleNext();
        UserRepository.setUserData(phoneNumber, null);
    }

    public static void userSettingsMenu() {
        Integer choise;
        do {
            UserRepository.showUserSettings(UserService.getCurrentLoggedInUser().getUsername());
            ConsoleUtils.writeConsoleLine("User Information:");
            ConsoleUtils.writeConsoleLine("1. Change Date Of Birth");
            ConsoleUtils.writeConsoleLine("2. Change Phone Number");
            ConsoleUtils.writeConsoleLine("0. Back");
            choise = ConsoleUtils.readConsoleInt();
            if (choise == 1)
                userSettingsDOBChange();
            else if(choise == 2)
                userSettingsPNChange();
        } while (choise != 0);
    }
}
