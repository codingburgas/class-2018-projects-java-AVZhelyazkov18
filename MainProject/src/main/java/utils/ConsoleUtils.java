package utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner = new Scanner(System.in);

    private static void resetScanner() {
        try {
            scanner.close();
        } catch (Exception e) {}
        scanner = new Scanner(System.in);
    }
    public static void writeConsoleLine(String line) {
        System.out.println(line);
    }

    public static void writeConsole(String input) { System.out.print(input); }
    public static String readConsoleLine() { return scanner.nextLine(); }
    public static Integer readConsoleInt() {
        try {
            return Integer.parseInt(readConsoleNext());
        } catch(NumberFormatException e) {
            return -1;
        } catch(NoSuchElementException e) {
            return -1;
        }
    }
    public static String readConsoleNext() { return scanner.next(); }

    public static String stringInput(String messageBeforeInput) {
        System.out.print(messageBeforeInput);
        return readConsoleNext();
    }

    public static void printTableForTop3(int maxSymbolsInALine, int maxSymbolsInTopicSlot, int symbolsInDateSlot) {
        System.out.print("+");
        for (int i = 0; i < maxSymbolsInALine; i++) {
            if(i == maxSymbolsInTopicSlot || i == maxSymbolsInTopicSlot + symbolsInDateSlot + 1) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
        }
        System.out.print("+");
        System.out.println();
    }
}
