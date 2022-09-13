package Login;

import utils.Display;

public class LoginMenuView {
    public LoginMenuView() {
        Display.clearScreen();
    }

    public void printUserNamePrompt() {
        System.out.print("Please Enter username: ");
    }

    public void printPasswordPrompt() {
        System.out.print("Please Enter password: ");
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
