package Login;

import homeMenu.HomeMenu;
import utils.Display;
import utils.Encrypt;
import utils.FileHandler;
import utils.User;

import java.util.List;
import java.util.Scanner;

public class LoginMenuController {
    private final LoginMenuModel model;
    private final LoginMenuView view;
    private final Scanner scanner;
    private List<User> users;
    private int userIndex;
    private final String role;

    public LoginMenuController(LoginMenuModel model, LoginMenuView view, String role) {
        this.model = model;
        this.view = view;
        this.role = role;
        this.scanner = new Scanner(System.in);
    }

    public void requestUserInput() {
        boolean isUserValid;
        users = FileHandler.readFromFile();
        if (listHasUsers()) return;
        view.printUserNamePrompt();
        String userName = scanner.nextLine();
        view.printPasswordPrompt();
        String password = scanner.nextLine();
        String encryptedPassword = Encrypt.encryptPassword(password);
        isUserValid = validateLoginDetails(userName, encryptedPassword);
        if (isUserValid) {
            validateRole();
        } else {
            view.printInvalidCred();
            requestUserInput();
        }
    }

    private boolean listHasUsers() {
        if (users.size() == 0) {
            view.printUsersEmpty();
            Display.returnMainMenu();
            new HomeMenu();
            return true;
        }
        return false;
    }

    private boolean validateLoginDetails(String userName, String encryptedPassword) {
        for (User userData : users) {
            if ((userData.getUserName().equals(userName)) && (userData.getPassword().equals(encryptedPassword))) {
                userIndex = users.indexOf(userData);
                return true;
            }
        }
        return false;
    }

    private void validateRole() {
        if ((users.get(userIndex).getUserRole().equalsIgnoreCase(role))) {
            model.handleOption(role);
        } else {
            view.printInvalidUser();
            Display.returnMainMenu();
            new HomeMenu();
        }
    }
}
