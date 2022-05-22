package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeConsoleLine(String line) {
        System.out.println(line);
    }
    
    public static String readConsoleLine() { return scanner.nextLine(); }
    public static Integer readConsoleInt() {
        try {
            return scanner.nextInt();
        } catch(InputMismatchException e) {
            return -1;
        }
    }
    public static String readConsoleNext() { return scanner.next(); }

    public static String stringInput(String messageBeforeInput) {
        System.out.print(messageBeforeInput);
        return readConsoleNext();
    }
}
