package Login;

import mvc.BaseView;

public class LoginMenuView extends BaseView {
    public LoginMenuView() {
        clearScreen();
    }

    public String printUserNamePrompt() {
        System.out.print("Please Enter username: ");
        return scanner.nextLine();
    }

    public String printPasswordPrompt() {
        System.out.print("Please Enter password: ");
        return scanner.nextLine();
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
