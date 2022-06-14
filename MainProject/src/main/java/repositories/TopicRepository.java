package repositories;

/*
    Layer #3: Data Access
*/

import repositories.models.Topic;
import services.UserService;
import utils.ApplicationProperties;
import utils.ConsoleUtils;

import java.io.Console;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TopicRepository {

    public static Topic[] getTop3TopicsByDate() {
        String query = "SELECT TOP 3 * FROM ForumPage ORDER BY ForumPageCreatedOn DESC;";

        Topic[] topics = new Topic[3];
        int currentTopic = 0;
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("ForumId");
                String forumTitle = rs.getString("ForumTitle");
                LocalDate creationDate = rs.getDate("ForumPageCreatedOn").toLocalDate();
                boolean solved = rs.getBoolean("ForumPageSolved");

                topics[currentTopic] = new Topic(id, forumTitle, creationDate, solved);
            }
            return topics;
        } catch (Exception e) {}

        return null;
    }

    public static void createNewTopic(Topic topic) {
        String topicQuery = "INSERT INTO ForumPage (ForumTitle, ForumDescription, ForumPageCreatedFrom, " +
                "ForumPageCreatedOn, ForumPageSolved, ForumPageDeleted)\n" +
                "VALUES (?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)){
            PreparedStatement statement = conn.prepareStatement(topicQuery);
            statement.setString(1, topic.getTopicTitle());
            statement.setString(2, topic.getTopicDescription());
            statement.setInt(3, UserService.getCurrentLoggedInUser().getUserId());
            statement.setDate(4, Date.valueOf(topic.getTopicCreationDate()));
            statement.setBoolean(5, false);
            statement.setBoolean(6, false);

            statement.execute();
            ConsoleUtils.writeConsoleLine("Successfully created forum page.");
        } catch (SQLException exception) {
            ConsoleUtils.writeConsoleLine("Unsuccessful connection to database.");
        }
    }

    public static void showUserTopics() {
        String showTopicsQuery = "SELECT * FROM ForumPage WHERE ForumPageCreatedFrom = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)){
            PreparedStatement preparedStatement = conn.prepareStatement(showTopicsQuery);
            preparedStatement.setInt(1, UserService.getCurrentLoggedInUser().getUserId());

            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 1;
            while (resultSet.next()) {
                ConsoleUtils.writeConsoleLine(count + ". " + resultSet.getString("ForumTitle") + " Solved: " +
                        (resultSet.getBoolean("ForumPageSolved") ? "Yes" : "No") + " Created On: " + resultSet.getDate("ForumPageCreatedOn").toString());
                count++;
            }
            if(count == 1) {
                ConsoleUtils.writeConsoleLine("No topics exist.");
            }
        } catch (SQLException exception) {
            ConsoleUtils.writeConsoleLine("Unsuccessful connection to database.");
        }
    }

    public static void deleteATopicById(Integer topicId) {
        String deleteTopicQuery = "DELETE FROM ForumPage WHERE ForumId = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteTopicQuery);
            preparedStatement.setInt(1, topicId);
            Integer rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
                ConsoleUtils.writeConsoleLine("Successfully deleted topic.");
            else
                ConsoleUtils.writeConsoleLine("Couldn't delete the topic.");
        } catch (SQLException exception) {
            exception.printStackTrace();
            ConsoleUtils.writeConsoleLine("Unsuccessful connection to database.");
        }
    }

    public static List<Topic> getUserTopicsReturnTopic() {
        List<Topic> topics = new ArrayList<>();
        String showTopicsQuery = "SELECT * FROM ForumPage WHERE ForumPageCreatedFrom = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)){
            PreparedStatement preparedStatement = conn.prepareStatement(showTopicsQuery);
            preparedStatement.setInt(1, UserService.getCurrentLoggedInUser().getUserId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                topics.add(new Topic(resultSet.getInt("ForumId"), resultSet.getString("ForumTitle"), resultSet.getDate("ForumPageCreatedOn").toLocalDate(), resultSet.getBoolean("ForumPageSolved")));
            }

            return topics;
        } catch (SQLException exception) {
            ConsoleUtils.writeConsoleLine("Unsuccessful connection to database.");
        }

        return null;
    }
}
