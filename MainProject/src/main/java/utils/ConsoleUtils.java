package utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeConsoleLine(String line) {
        System.out.println(line);
    }
    
    public static String readConsoleLine() { return scanner.nextLine(); }
    public static Integer readConsoleInt() { return scanner.nextInt(); }
    public static String readConsoleNext() { return scanner.next(); }

    public static void clearConsole() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch(Exception e) {
            System.out.println("Couldn't clear screen. Please contact the developers of the app.");
        }
    }
    public static String stringInput(String messageBeforeInput) {
        System.out.print(messageBeforeInput);
        return readConsoleNext();
    }
}
