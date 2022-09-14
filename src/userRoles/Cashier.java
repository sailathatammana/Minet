package userRoles;

import cashier.CashierMenu;
import utils.User;

public class Cashier implements iCashier {
    User user;
    public Cashier(User user){
        this.user = user;
    }
    @Override
    public void sellItem() {
        System.out.println(user.getFullName());
        System.out.println("Sell Item");
    }

    @Override
    public void createOrder() {
        System.out.println("Create Order");
    }

    @Override
    public void returnItem() {
        System.out.println("Return Item");
    }

    @Override
    public void viewInventory() {
        System.out.println("View Inventory");
    }
}
