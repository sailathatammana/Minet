package com.minet.manager;

import com.minet.userRoles.Manager;
import com.minet.utils.User;

public class ManagerMenu {
    ManagerMenuController controller = new ManagerMenuController();

    public void start(User user) {
        Manager manager = new Manager(user);
        while (true) {
            String cashierAction = controller.run();
            if (controller.model.getMenuOptions().contains(cashierAction)) {
                switch (cashierAction) {
                    case "View Inventory" -> manager.viewInventory();
                    case "Review orderList" -> manager.viewOrderList();
                    case "View OrderedTransaction List" -> manager.viewOrderedTransactionList();
                }
            }
        }
    }
}

