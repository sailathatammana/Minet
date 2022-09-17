package manager;

import userRoles.Manager;
import utils.User;

public class ManagerMenu {
    ManagerMenuController controller = new ManagerMenuController();

    public void start(User user) {
        Manager manager = new Manager(user);
        while (true) {
            String cashierAction = controller.run();
            if (controller.model.menuOptions.contains(cashierAction)) {
                switch (cashierAction) {
                    case "View Inventory" -> manager.viewInventory();
                    case "Review orderList" -> manager.viewOrderList();
                }
            }
        }
    }
}

