package manager;

import data.OrderListPool;
import userRoles.Manager;
import utils.OrderList;
import utils.User;

import java.util.List;

public class ManagerMenu {
    ManagerMenuController controller = new ManagerMenuController();

    public void start(User user) {
        List<OrderList> orderLists = OrderListPool.getAllOrderLists();
        Manager manager = new Manager(user, orderLists);
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

