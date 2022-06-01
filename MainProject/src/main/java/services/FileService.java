package services;

import repositories.UserRepository;
import repositories.models.User;
import utils.ConsoleUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class FileService {
    private static File cookieFile;

    private static void createFile() {
        try {
            File myObj = new File(getUsersProjectRootDirectory().toString() + "\\cookie.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            ConsoleUtils.writeConsoleLine("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Path getUsersProjectRootDirectory() {
        String envRootDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        if (rootDir.startsWith(envRootDir)) {
            return rootDir;
        } else {
            throw new RuntimeException("Root dir not found in user directory.");
        }
    }

    public static void findCookieFile() {
        String path = getUsersProjectRootDirectory().toString() + "\\cookie.txt";

        File newFile = new File(path);
        if(newFile.exists()) {
            cookieFile = newFile.getAbsoluteFile();
            newFile = null;
        } else {
            createFile();
        }
    }

    public static void loadUser() {
        String path = getUsersProjectRootDirectory().toString() + "\\cookie.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String[] words = br.readLine().split(";");
            words[0].trim();
            words[1].trim();

            if (words[0] != "" && words[1] != "")
                UserService.setCurrentLoggedInUser(UserRepository.loginUser(words[0], words[1]));

            br.close();
        } catch (Exception e) {}
    }

    public static void saveUser() {
        if(UserService.getCurrentLoggedInUser() == null) return;

        User user = UserService.getCurrentLoggedInUser();

        String saveString = user.getUsername() + ";" + user.getPassword();

        String path = getUsersProjectRootDirectory().toString() + "\\cookie.txt";

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(path));

            br.write(saveString);
            br.close();
        } catch (Exception e) {}
    }
}
