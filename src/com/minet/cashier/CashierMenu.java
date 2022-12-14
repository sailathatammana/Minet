package com.minet.cashier;

import com.minet.userRoles.Cashier;
import com.minet.utils.User;

public class CashierMenu implements Runnable {
    CashierMenuController controller;
    User user;

    public CashierMenu(User user) {
        this.controller = new CashierMenuController();
        this.user = user;
    }

    public void started() {
        Cashier cashier = new Cashier(user);
        while (true) {
            String cashierAction = controller.run();
            if (controller.model.getMenuOptions().contains(cashierAction)) {
                switch (cashierAction) {
                    case "Sell an Item" -> cashier.sellItem();
                    case "Return an Item" -> cashier.returnItem();
                    case "Create order" -> cashier.createOrder();
                    case "View Inventory" -> cashier.viewInventory();
                    case "View orderList" -> cashier.viewOrderList();
                }
            }
        }
    }

    @Override
    public void run() {
        started();
    }
}
