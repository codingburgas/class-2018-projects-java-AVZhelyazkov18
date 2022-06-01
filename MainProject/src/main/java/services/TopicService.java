package services;

import repositories.TopicRepository;
import repositories.models.Topic;
import utils.ConsoleUtils;
import utils.StringUtils;

public class TopicService {
    private static final int maxSymbolsInALine = 80;
    private static final int maxSymbolsInTopicSlot = 49;
    private static final int maxSymbolsInUserNameSlot = 20;
    private static final int symbolsInDateSlot = 10;
    public static void getMainTopicsList() {
        ConsoleUtils.printTableForTop3(maxSymbolsInALine, maxSymbolsInTopicSlot, symbolsInDateSlot);

        Topic[] topics = TopicRepository.getTop3TopicsByDate();
        for(int i = 0; i < 3; i++) {
            ConsoleUtils.writeConsole("|");
            if(topics[i] != null) {
                ConsoleUtils.writeConsole((char)(64+i) + ". ");
                ConsoleUtils.writeConsole(StringUtils.sub(topics[i].getForumTitle(), 0, maxSymbolsInTopicSlot - 3, true));
                ConsoleUtils.writeConsole("|");
                ConsoleUtils.writeConsole(topics[i].getCreationDate().toString());
                ConsoleUtils.writeConsole("|");
                ConsoleUtils.writeConsole(StringUtils.sub(topics[i].getPageCreatedFrom(), 0, maxSymbolsInUserNameSlot, true));
                ConsoleUtils.writeConsole("|");
            } else {
                for(int j = 0; j < maxSymbolsInALine; j++) {
                    if(j == maxSymbolsInTopicSlot || j == maxSymbolsInTopicSlot + symbolsInDateSlot + 1) {
                        ConsoleUtils.writeConsole("|");
                    } else {
                        ConsoleUtils.writeConsole(" ");
                    }
                }
                ConsoleUtils.writeConsole("|");
            }
            ConsoleUtils.writeConsoleLine("");
        }
        
        ConsoleUtils.printTableForTop3(maxSymbolsInALine, maxSymbolsInTopicSlot, symbolsInDateSlot);
    }
}
