package utils;

import java.util.Objects;
import java.util.Scanner;

public class Display {
    static Scanner scanner = new Scanner(System.in);

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
