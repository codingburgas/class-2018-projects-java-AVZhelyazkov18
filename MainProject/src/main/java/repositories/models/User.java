package repositories.models;

import java.util.Objects;

public class User {
    private int userId;
    private String username;
    private String password;

    private String email;

    // Constructor
    public User(int userId, String username, String password, String userEmail) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = userEmail;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void removeUser() {
        this.userId = 0;
        this.password = null;
        this.username = null;
    }
}
