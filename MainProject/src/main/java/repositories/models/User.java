package repositories.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long userId;
    private String username;
    private String password;
    private List<Project> projects;

    // Constructor
    public User(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.projects = new ArrayList<>();
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    // Overriding Equals() and hashcode() in order for the model to be "compatible" with "Hash Collections", e.g.
    // HashMap, HashSet.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", projects=" + projects +
                '}';
    }
}
