package main;

import controllers.UserController;
import repositories.UserRepository;
import repositories.models.User;
import services.ActionService;
import services.FileService;
import utils.ApplicationProperties;

import java.sql.*;

// Main class containing main method, i.e. start of a Java application
public class App {
    public static void main(String... args) {
        // Initialization Method
        App.mainInitialization();

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.MAINURL);) {
            UserController.askUserAction();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        App.mainDeconstruction();
    }

    public static void mainInitialization() {
        ApplicationProperties._init(2);
        FileService.findCookieFile();
        FileService.loadUser();
    }

    public static void mainDeconstruction() {
        FileService.saveUser();
    }
}
