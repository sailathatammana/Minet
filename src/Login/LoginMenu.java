package Login;


import homeMenu.HomeMenu;
import utils.Display;
import utils.User;

import java.util.List;

public class LoginMenu {
    LoginMenuController controller;
    private int userIndex;
    private final String role;
    protected List<User> users;

    public LoginMenu(String role) {
        this.role = role;
        controller = new LoginMenuController(role);
        users = controller.model.getAllUsers();
    }

    public void start() {
        while (true) {
            User user = controller.run();
            boolean isUserValid = validateLoginDetails(user.getUserName(), user.getPassword());
            if (isUserValid) {
                validateRole();
                break;
            } else {
                controller.view.printInvalidCred();
            }
        }
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
            this.handleOption(role);
        } else {
            controller.view.printInvalidUser();
            Display.returnMainMenu();
            HomeMenu homeMenu = new HomeMenu();
            homeMenu.start();
        }
    }

    public void handleOption(String role) {
        switch (role) {
            case "Cashier" -> System.out.println("Cashier");
            case "Manager" -> System.out.println("Manager");
            case "Admin" -> System.out.println("Admin");
        }

    }
}
