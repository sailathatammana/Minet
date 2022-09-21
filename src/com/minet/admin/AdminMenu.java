package com.minet.admin;

import com.minet.userRoles.Admin;
import com.minet.utils.User;

public class AdminMenu {
    AdminMenuController controller = new AdminMenuController();

    public void start(User user) {
        Admin admin = new Admin(user);
        while (true) {
            String cashierAction = controller.run();
            if (controller.model.getMenuOptions().contains(cashierAction)) {
                switch (cashierAction) {
                    case "Sell an Item" -> admin.sellItem();
                    case "Return an Item" -> admin.returnItem();
                    case "Create order" -> admin.createOrder();
                    case "View Inventory" -> admin.viewInventory();
                    case "Review orderList" -> admin.viewOrderList();
                    case "View OrderedTransaction List" -> admin.viewOrderedTransactionList();
                }
            }
        }
    }
}
