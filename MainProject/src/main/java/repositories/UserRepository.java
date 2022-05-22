package repositories;

import java.sql.*;
import java.util.*;

import repositories.models.User;
import utils.ApplicationProperties;
import utils.SQLUtils;

/*
    Layer #3: Data Access
*/
public class UserRepository {
    public static boolean checkIfUserNameAlreadyExists(String userName) {
        String query = "SELECT COUNT(*) FROM [ProjectDB].[dbo].[User] WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userName);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            if(resultSet.getInt(resultSet.getRow()) > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addUserToUserTable(String userName, String userEmail, String userPassword) {
        int lastUserId = SQLUtils.countDataAmountFromTable("User", "*");

        String query = "INSERT INTO [ProjectDB].[dbo].[User] (UserId , Username, Password, UserEmail) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)){
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, lastUserId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setString(4, userPassword);

            preparedStatement.executeUpdate();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
