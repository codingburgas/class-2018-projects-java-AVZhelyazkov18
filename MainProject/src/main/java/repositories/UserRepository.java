package repositories;

import java.sql.*;

import repositories.models.User;
import utils.ApplicationProperties;
import utils.SQLUtils;

/*
    Layer #3: Data Access
*/
public class UserRepository {
    public static boolean checkIfUserNameAlreadyExists(String userName) {
        String query = "SELECT COUNT(*) FROM [ProjectDB].[dbo].[User] WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.MAINURL);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userName);

            ResultSet resultSet = ps.executeQuery();
            //resultSet.next();
            try {
                if (resultSet.getInt(resultSet.getRow()) > 0) {
                    return true;
                }
            } catch(Exception e) {} // Would Throw Exception If Users are empty in UserTable
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addUserToUserTable(String userName, String userEmail, String userPassword) {
        int lastUserId = SQLUtils.countDataAmountFromTable("User", "*");

        String query = "INSERT INTO [ProjectDB].[dbo].[User] (UserId , Username, Password, UserEmail) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.MAINURL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, lastUserId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userPassword);
            preparedStatement.setString(4, userEmail);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User loginUser(String username, String password) throws SQLException {
        String query = "SELECT UserId FROM [ProjectDB].[dbo].[User] WHERE Username = ? AND Password = ?";

        Connection conn = DriverManager.getConnection(ApplicationProperties.MAINURL);

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();

        try {
            int result = resultSet.getInt(resultSet.getRow());
            return new User(result, username, password);
        } catch (SQLException e) {}
        return null;
    }
}
