package repositories;

import repositories.models.Project;
import utils.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    List<Project> getProjects() {
        List<Project> listOfProjects = new ArrayList<>();
        String query = "SELECT * FROM projects;";
        // Connecting to a database steps:
        // 1. Get Connection using DriverManager via jdbc_url, username, and password
        // 2. Get PreparedStatement from Connection instance passing query
        // 3. Execute PreparedStatement query and get the ResultSet
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Project project = mapToProject(resultSet);
                listOfProjects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfProjects;
    }

    public List<Project> getProjectsForUser(Long userId) {
        List<Project> listOfProjects = new ArrayList<>();
        String query = "SELECT projects.* FROM projects_users JOIN projects ON projects.project_id = projects_users.projects_id WHERE user_id = ?;";
        // Connecting to a database steps:
        // 1. Get Connection using DriverManager via jdbc_url, username, and password
        // 2. Get PreparedStatement from Connection instance passing query
        // 3. Execute PreparedStatement query and get the ResultSet
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, userId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Project project = mapToProject(resultSet);
                listOfProjects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfProjects;
    }

    private Project mapToProject(ResultSet resultSet) throws SQLException {
        Long projectId = resultSet.getLong("userId_column");
        String projectName = resultSet.getString("username_column");
        String projectDescription = resultSet.getString("password_column");
        Project project = new Project(projectId, projectName, projectDescription);
        return project;
    }
}
