package com.minet.mvc;

import com.minet.utils.Display;

import java.util.List;
import java.util.Scanner;

public abstract class BaseView {
    protected final Scanner scanner = new Scanner(System.in);

    public void clearScreen() {
        Display.clearScreen();
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
