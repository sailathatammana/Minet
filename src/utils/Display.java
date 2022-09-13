package utils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Display {
    static Scanner scanner = new Scanner(System.in);

    private static void welcomeMsg() {
        System.out.println("Welcome to Warehouse.\n");
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Display.welcomeMsg();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
                Display.welcomeMsg();
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static void chooseOption() {
        System.out.print("Please choose an option: ");
    }

    public static void displayList(List<String> options) {
        for (int i = 1; i <= options.size(); i++) {
            System.out.println("[" + i + "]" + " " + options.get(i - 1));
        }
    }

    public static void printInvalidOption() {
        System.out.println("⚠️ Invalid option");
    }

    public static void exit() {
        System.out.println("Thank you for using the ATM Console!");
        System.exit(1);
    }

    public static void returnMainMenu() {
        System.out.print("Press `q` to return to main menu: ");
        while ((true)) {
            String option = scanner.nextLine();
            if (checkInput(option)) return;
            else {
                System.out.print("You entered wrong input please press q to return to main menu: ");
            }
        }
    }

    public static boolean checkInput(String input) {
        return Objects.equals(input.toLowerCase(), "q");
    }

}
