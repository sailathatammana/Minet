package mvc;

import java.util.List;
import java.util.Scanner;

public abstract class BaseView {
    protected final Scanner scanner = new Scanner(System.in);

    private void welcomeMsg() {
        System.out.println("Welcome to Warehouse.\n");
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                welcomeMsg();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
                welcomeMsg();
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public String chooseOption() {
        System.out.print("Please choose an option: ");
        return scanner.nextLine();
    }

    public void displayList(List<String> options) {
        for (int i = 1; i <= options.size(); i++) {
            System.out.println("[" + i + "]" + " " + options.get(i - 1));
        }
    }

    public void printStringInvalidOption() {
        System.out.println("⚠️ Please Enter a number");
    }

    public void printNumberInvalidOption() {
        System.out.println("⚠️ Please Enter a valid number");
    }

}
