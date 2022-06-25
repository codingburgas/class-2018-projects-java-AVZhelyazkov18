package repositories;

import repositories.models.Topic;
import repositories.models.User;
import utils.ApplicationProperties;
import utils.ConsoleUtils;

import java.sql.*;
import java.time.LocalDate;

public class CommentRepository {
    public static void createCommentToTopic(String comment, String userName, Integer topicId) {
        User foundUser = UserRepository.getUserFromUsername(userName);
        String createCommentQuery = "INSERT INTO Comment (CommentCreatorId, CommentCreationDate, CommentContent, CommentDeleted, CommentForumPageOn, CommentPosition) VALUES (?,?,?,?,?,?)";
        String countAllMessagesQuerey = "SELECT MAX(CommentPosition) AS 'Max' FROM Comment WHERE CommentForumPageOn = " + topicId.toString();
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(countAllMessagesQuerey);
            rs.next();
            Integer nextNumber = rs.getInt("Max") + 1;
            PreparedStatement ps = conn.prepareStatement(createCommentQuery);
            ps.setInt(1, foundUser.getUserId());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setString(3, comment);
            ps.setBoolean(4, false);
            ps.setInt(5, topicId);
            ps.setInt(6, nextNumber);
            ps.execute();
        } catch (SQLException exc) {
            System.out.println("Problem with SSMS.");
        }
    }

    public static void showAllCommentsToTopic(Topic topic) {
        String createCommentQuery = "SELECT * FROM Comment WHERE CommentForumPageOn = " + topic.getTopicTitle() + "ORDER BY CommentPosition ASC";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createCommentQuery);
            int count = 1;

            User user = UserRepository.getUserFromId(rs.getInt("CommentCreatorId"));

            if(user==null)
                return;

            while(rs.next()) {
                ConsoleUtils.writeConsoleLine(count + ". " + rs.getDate("CommentCreationDate").toString() + "[" + user.getUsername() + "] " + rs.getString("CommentContent"));
                count++;
            }
        } catch (SQLException exc) {
            System.out.println("Problem with SSMS.");
        }
    }
}
