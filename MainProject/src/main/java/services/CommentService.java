package services;

import repositories.CommentRepository;
import repositories.models.Topic;

public class CommentService {
    public static void createComment(String input, Integer topicId) {
        if (input.length() > 0) {
            if (input.charAt(0) == '~') {
                CommentRepository.createCommentToTopic(input.substring(1), UserService.getCurrentLoggedInUser().getUsername(), topicId);
            }
        }
    }
    public static void showAllCommentsInTopic(Topic topic) {
        CommentRepository.showAllCommentsToTopic(topic);
    }
}
