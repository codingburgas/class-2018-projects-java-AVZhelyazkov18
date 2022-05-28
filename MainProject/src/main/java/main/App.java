package main;

import controllers.UserController;
import repositories.models.User;
import services.ActionService;
import services.FileService;
import utils.ApplicationProperties;

import java.sql.*;

// Main class containing main method, i.e. start of a Java application
public class App {
    public static void main(String... args) {
        FileService.findCookieFile();

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);) {
            UserController.askUserAction();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
