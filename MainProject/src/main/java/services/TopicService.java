package services;

import repositories.TopicRepository;
import repositories.models.Topic;
import utils.ConsoleUtils;

import java.util.List;

public class TopicService {

    public static void createTopic() {
        ConsoleUtils.writeConsoleLine("Enter Forum Title: ");
        ConsoleUtils.readConsoleLine();
        String title = ConsoleUtils.readConsoleLine();
        if(title.length() <= 10) {
            ConsoleUtils.writeConsoleLine("Title must have at least 10 characters.");
            return;
        }
        ConsoleUtils.writeConsole("(Optional) Enter Forum Description: ");
        String description = ConsoleUtils.readConsoleLine();

        Topic topic = new Topic(title, description);

        TopicRepository.createNewTopic(topic);
    }
    public static void findTopic() {
        ConsoleUtils.writeConsoleLine("Search topic by title.");
        ConsoleUtils.readConsoleLine();
        String userInput = ConsoleUtils.readConsoleLine();
        TopicRepository.findTopicByTopicName(userInput);
    }

    public static void showUserTopics() {
        TopicRepository.showUserTopics();
    }


    public static List<Topic> getCurrentUserTopicList() {
        return TopicRepository.getUserTopicsReturnTopic();
    }
    public static void removeTopic(Integer id) {
        TopicRepository.deleteATopicById(id);
    }
}
