package main;

import controllers.UserController;
import repositories.TopicRepository;
import repositories.UserRepository;
import repositories.models.User;
import services.ActionService;
import services.FileService;
import utils.ApplicationProperties;
import utils.StringUtils;

import java.sql.*;

// Main class containing main method, i.e. start of a Java application
public class App {
    public static void main(String... args) {
        // Initialization Method
        App.mainInitialization();

        StringUtils.sub("HelloWorld!", 0, 5, false);
        StringUtils.sub("HelloWorld!", 0, 20, true);


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
