package controllers;

import repositories.TopicRepository;
import repositories.models.Topic;
import services.TopicService;
import utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;

public class TopicController {
    public static void askUserTopicAction() {
        int chosenOption = 0;
        do {
            mainTopicMessageMenu();
            chosenOption = ConsoleUtils.readConsoleInt();

            if (chosenOption == 1)
                TopicService.createTopic();
            else if (chosenOption == 3)
                TopicService.showUserTopics();
            else if (chosenOption == 4)
                askUserDeleteTopicAction();
        } while (chosenOption != 0);
    }

    private static void askUserDeleteTopicAction() {
        List<Topic> topics = TopicService.getCurrentUserTopicList();
        if (topics.size() < 1) {
            ConsoleUtils.writeConsoleLine("There are no existing topics.");
            return;
        }
        int chosenOption = -1;
        while (chosenOption != 0) {
            ConsoleUtils.writeConsoleLine("Enter Topic Number for deletion: ");
            TopicService.showUserTopics();
            ConsoleUtils.writeConsoleLine("0. Go Back");
            chosenOption = ConsoleUtils.readConsoleInt();
            if (chosenOption == -1) {
                ConsoleUtils.writeConsoleLine("Please enter a valid number.");
            } else if (chosenOption == 0) {}
            else {
                try {
                    TopicService.removeTopic(topics.get(chosenOption - 1).getTopicId());
                    chosenOption = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void mainTopicMessageMenu() {
        ConsoleUtils.writeConsoleLine("Choose an option: ");
        ConsoleUtils.writeConsoleLine("1. Create a topic.");
        ConsoleUtils.writeConsoleLine("2. Search a topic.");
        ConsoleUtils.writeConsoleLine("3. Show my topics.");
        ConsoleUtils.writeConsoleLine("4. Delete a topic.");
        ConsoleUtils.writeConsoleLine("0. Go Back");
    }
}
