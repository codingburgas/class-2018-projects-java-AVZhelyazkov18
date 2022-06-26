package repositories;

import java.sql.*;
import java.time.LocalDate;

import repositories.models.User;
import services.UserService;
import utils.ApplicationProperties;
import utils.SQLUtils;
import utils.ConsoleUtils;

/*
    Layer #3: Data Access
*/
public class UserRepository {
    public static User getUserFromUsername(String userName) {
        String getUserQuery = "SELECT * FROM [ProjectDB].[dbo].[User] WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL); PreparedStatement ps = conn.prepareStatement(getUserQuery)) {
            ps.setString(1, userName);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            try {
                return new User(resultSet.getInt("UserId"), resultSet.getString("Username"), resultSet.getString("Password"), resultSet.getString("UserEmail"));
            } catch(Exception e) {} // Would Throw Exception If Users are empty in UserTable
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserFromId(Integer userId) {
        String getUserQuery = "SELECT * FROM [ProjectDB].[dbo].[User] WHERE UserId = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL); PreparedStatement ps = conn.prepareStatement(getUserQuery)) {
            ps.setInt(1, userId);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            try {
                return new User(resultSet.getInt("UserId"), resultSet.getString("Username"), resultSet.getString("Password"), resultSet.getString("UserEmail"));
            } catch(Exception e) {} // Would Throw Exception If Users are empty in UserTable
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean checkIfUserNameAlreadyExists(String userName) {
        String query = "SELECT COUNT(*) AS 'Counter' FROM [ProjectDB].[dbo].[User] WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userName);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            try {
                if (resultSet.getInt("Counter") > 0) {
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

        if(checkIfUserNameAlreadyExists(userName)) {
            ConsoleUtils.writeConsoleLine("Username already exists. Please use another one.");
            return false;
        }

        String query = "INSERT INTO [ProjectDB].[dbo].[User] (Username, Password, UserEmail) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userEmail);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void showUserSettings(String userName) {
        String query = "SELECT * FROM [ProjectDB].[dbo].[User] WHERE Username = ?";
        try(Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Date dob = rs.getDate("UserDateOfBirth");
            String phoneNumber = rs.getString("UserPhoneNumber");
            String userEmail = rs.getString("UserEmail");



            ConsoleUtils.writeConsoleLine("Username: " + userName);
            ConsoleUtils.writeConsoleLine("Email: " + userEmail);
            ConsoleUtils.writeConsoleLine("Password: ********");
            if(dob == null)
                ConsoleUtils.writeConsoleLine("Date Of Birth: N/A");
            else
                ConsoleUtils.writeConsoleLine("Date Of Birth: " + dob.toString());
            if(phoneNumber == null || phoneNumber.trim() == "")
                ConsoleUtils.writeConsoleLine("Phone Number: N/A");
            else
                ConsoleUtils.writeConsoleLine("Phone Number: " + phoneNumber);
        } catch(SQLException exc) {
            exc.printStackTrace();
        }
    }

    public static void setUserData(String phoneNumber, Date DOB) {
        String phoneNumberQuery = "UPDATE [ProjectDB].[dbo].[User] SET UserPhoneNumber = ? WHERE UserId = ?";
        String DOBQuery = "UPDATE [ProjectDB].[dbo].[User] SET UserDateOfBirth = ? WHERE UserId = ?";
        try(Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            if(phoneNumber != null) {
                PreparedStatement ps = conn.prepareStatement(phoneNumberQuery);
                ps.setString(1, phoneNumber);
                ps.setInt(2, UserService.getCurrentLoggedInUser().getUserId());

                Boolean success = ps.execute();

                if(success)
                    ConsoleUtils.writeConsoleLine("Phone Number has been changed.");
            }
            if(DOB != null) {
                PreparedStatement ps = conn.prepareStatement(DOBQuery);
                ps.setDate(1, DOB);
                ps.setInt(2, UserService.getCurrentLoggedInUser().getUserId());

                Boolean success = ps.execute();

                if(success)
                    ConsoleUtils.writeConsoleLine("Date Of Birth has been changed.");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

    }
    public static User loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM [ProjectDB].[dbo].[User] WHERE Username = ? AND Password = ?";

        Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();

        try {
            return new User(resultSet.getInt("UserId"), resultSet.getString("Username"), resultSet.getString("Password"), resultSet.getString("UserEmail"));
        } catch (SQLException e) {}
        return null;
    }
}
