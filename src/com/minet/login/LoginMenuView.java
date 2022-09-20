package com.minet.login;

import com.minet.mvc.BaseView;

import java.io.Console;

public class LoginMenuView extends BaseView {
    public LoginMenuView() {
        clearScreen();
    }

    public String printUserNamePrompt() {
        System.out.print("Please Enter username: ");
        return scanner.nextLine();
    }

    public String printPasswordPrompt() {
        Console console = System.console();
        if (console == null) {
            System.out.print("Please Enter password: ");
            return scanner.nextLine();
        }
        System.out.print("Please Enter password: ");
        char[] password = console.readPassword();
        return String.valueOf(password);
    }

    public void printInvalidCred() {
        System.out.println("⚠️ Invalid Credentials ");
    }

    public void printInvalidUser() {
        System.out.println("⚠️ Unauthorized Login ");
    }

    public void printUsersEmpty() {
        System.out.println(" No users available to login ");
    }
}
