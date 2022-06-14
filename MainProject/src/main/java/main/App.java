package main;

import controllers.UserController;
import services.FileService;
import utils.ApplicationProperties;

import java.sql.*;

// Main class containing main method, i.e. start of a Java application
public class App {
    public static void main(String... args) {
        UserController.welcomeMessage();
        FileService.findCookieFile();
        FileService.loadUser();

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);) {
            UserController.askUserAction();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        FileService.saveUser();
    }
}
