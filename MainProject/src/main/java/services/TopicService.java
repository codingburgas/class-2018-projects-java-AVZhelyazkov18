package services;

import repositories.TopicRepository;
import repositories.models.Topic;
import utils.ConsoleUtils;

import java.io.Console;
import java.util.List;

public class TopicService {
    public static void getTop3MostRecentTopics() {

    }

    public static void createTopic() {
        ConsoleUtils.writeConsoleLine("Enter Forum Title: ");
        ConsoleUtils.readConsoleLine();
        String title = ConsoleUtils.readConsoleLine();
        if(title.length() <= 10) {
            ConsoleUtils.writeConsoleLine("Title must have at least 10 characters.");
            return;
        }
        System.out.println("(Optional) Enter Forum Description: ");
        String description = ConsoleUtils.readConsoleLine();

        Topic topic = new Topic(title, description);

        TopicRepository.createNewTopic(topic);
    }

    public static void findTopic() {

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
