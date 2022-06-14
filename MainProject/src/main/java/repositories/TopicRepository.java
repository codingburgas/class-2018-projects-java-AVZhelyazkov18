package repositories;

/*
    Layer #3: Data Access
*/

import repositories.models.Topic;
import repositories.models.User;
import utils.ApplicationProperties;

import java.sql.*;

public class TopicRepository {

    public static Topic[] getTop3TopicsByDate() {
        String query = "SELECT TOP 3 * FROM ForumPage ORDER BY ForumPageCreatedOn DESC;";

        Topic[] topics = new Topic[3];
        int currentTopic = 0;
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.MAINURL)) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("ForumId");
                String forumTitle = rs.getString("ForumTitle");
                Date creationDate = rs.getDate("ForumPageCreatedOn");
                boolean solved = rs.getBoolean("ForumPageSolved");

                topics[currentTopic] = new Topic(id, forumTitle, creationDate, solved);
            }
            return topics;
        } catch (Exception e) {}

        return null;
    }
}
