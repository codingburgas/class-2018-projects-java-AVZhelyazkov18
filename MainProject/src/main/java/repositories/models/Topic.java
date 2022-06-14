package repositories.models;

import java.sql.Date;

public class Topic {
    private Integer ForumId;
    private String ForumTitle;
    private Date CreationDate;
    private Boolean solved;
    private String pageCreatedFrom;

    public Topic(Integer forumId, String forumTitle, Date creationDate, Boolean solved) {
        ForumId = forumId;
        ForumTitle = forumTitle;
        CreationDate = creationDate;
        this.solved = solved;
    }

    public Integer getForumId() {
        return ForumId;
    }

    public String getForumTitle() {
        return ForumTitle;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public Boolean getSolved() {
        return solved;
    }

    public String getPageCreatedFrom() {
        return pageCreatedFrom;
    }
}
