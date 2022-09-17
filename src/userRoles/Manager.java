package userRoles;

import utils.Display;
import utils.OrderList;
import utils.User;
import viewOrderList.ViewOrderList;

import java.util.List;

public class Manager extends Person implements iManager {
    User user;
    private final List<OrderList> orderLists;

    public Manager(User user, List<OrderList> orderLists) {
        this.user = user;
        this.orderLists = orderLists;
    }


    @Override
    public void viewOrderList() {
        new ViewOrderList(orderLists);
        Display.returnMainMenu();
    }
}
