package services;

import utils.ConsoleUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    private static File cookieFile;

    private static void createFile() {
        try {
            File myObj = new File(getUsersProjectRootDirectory().toString() + "\\cookie.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Path getUsersProjectRootDirectory() {
        String envRootDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        if (rootDir.startsWith(envRootDir))
            return rootDir;
        else
            throw new RuntimeException("Root dir not found in user directory.");
    }

    public static void findCookieFile() {
        String path = getUsersProjectRootDirectory().toString() + "\\cookie.txt";

        File newFile = new File(path);
        if(newFile.exists()) {
            cookieFile = newFile.getAbsoluteFile();
        } else
            createFile();
    }
}
