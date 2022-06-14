package controllers;

import services.UserService;
import utils.ConsoleUtils;

import java.util.Scanner;

public class TopicController {
    public static void askUserTopicAction(Scanner sc) {
        int chosenOption = 0;
        do {
            mainTopicMessageMenu();
            chosenOption = Integer.parseInt(sc.next());

        } while (chosenOption != 0);
    }


    public static void mainTopicMessageMenu() {
        System.out.println("Choose an option: ");
        System.out.println("1. Create a topic.");
        System.out.println("2. Search a topic.");
        System.out.println("3. Show my topics.");
        System.out.println("4. Delete a topic.");
    }
}
