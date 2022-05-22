package main;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import controllers.UserController;
import repositories.UserRepository;
import services.ActionService;
import services.FileService;
import services.UserService;
import utils.ApplicationProperties;

import java.sql.*;

// Main class containing main method, i.e. start of a Java application
public class App {
    public static void main( String... args ) {
        // 1. Creating Repository object to retrieve and handle data
        //UserRepository repository = new UserRepository();
        // 2. Creating Service object to handle business logic
        //UserService service = new UserService(repository);
        // 3. Creating Controller object to handle user input
        //UserController controller = new UserController(service);

        // Displaying users from a database
        //controller.displayUsersWithLongUsernames();
        FileService.getUsersProjectRootDirectory();

        FileService.findCookieFile();

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);) {
            System.out.println("Established a connection between SSMS and JDBC.");

            ActionService.askUserAction();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}
