package repositories.models;

import services.UserService;

import java.time.LocalDate;
import java.time.ZoneId;

public class Topic {
    private Integer topicId;
    private String topicTitle;
    private LocalDate topicCreationDate;
    private Boolean solved;
    private String pageCreatedFrom;
    private String topicDescription;

    public Topic(Integer forumId, String forumTitle, LocalDate creationDate, Boolean solved) {
        topicTitle = forumTitle;
        topicCreationDate = creationDate;
        this.solved = solved;
        this.topicId = forumId;
    }

    public Topic(String title, String description) {
        this.topicTitle = title;
        this.topicDescription = description;
        this.pageCreatedFrom = UserService.getCurrentLoggedInUser().getUsername();
        this.solved = false;
        this.topicCreationDate = LocalDate.now();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public LocalDate getTopicCreationDate() {
        return topicCreationDate;
    }

    public Boolean getSolved() {
        return solved;
    }

    public String getPageCreatedFrom() {
        return pageCreatedFrom;
    }

    public String getTopicDescription() {
        return topicDescription;
    }
}
