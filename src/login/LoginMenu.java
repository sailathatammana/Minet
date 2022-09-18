package login;

import cashier.CashierMenu;
import homeMenu.HomeMenu;
import manager.ManagerMenu;
import utils.Display;
import utils.User;

public class LoginMenu {
    LoginMenuController controller;
    private int userIndex;
    private final String role;
    User user;

    public LoginMenu(String role) {
        this.role = role;
        controller = new LoginMenuController(role);
    }

    public void start() {
        while (true) {
            user = controller.run();
            boolean isUserValid = validateLoginDetails(user.getUserName(), user.getPassword());
            if (isUserValid) {
                validateRole();
                break;
            } else {
                controller.view.printInvalidCred();
            }
        }
    }

    public boolean validateLoginDetails(String userName, String encryptedPassword) {
        var user = controller.model.users.stream()
                .filter(item -> (item.getUserName().equals(userName)) && (item.getPassword().equals(encryptedPassword)))
                .findFirst();
        boolean check = user.isPresent();
        user.ifPresent((userId) -> userIndex = controller.model.users.indexOf(user.get()));
        return check;
    }

    private void validateRole() {
        if ((controller.model.users.get(userIndex).getUserRole().equalsIgnoreCase(role))) {
            this.handleOption();
        } else {
            controller.view.printInvalidUser();
            Display.returnMainMenu();
            HomeMenu homeMenu = new HomeMenu();
            homeMenu.start();
        }
    }

    public void handleOption() {
        User currentUser = controller.model.users.get(userIndex);
        String userRole = controller.model.users.get(userIndex).getUserRole();
        switch (userRole.toLowerCase()) {
            case "cashier" -> {
                CashierMenu cashierMenu = new CashierMenu(currentUser);
                Thread thread = new Thread(cashierMenu);
                thread.start();
            }
            case "manager" -> {
                ManagerMenu managerMenu = new ManagerMenu();
                managerMenu.start(currentUser);
            }
            case "admin" -> System.out.println("Admin");
        }

    }
}
