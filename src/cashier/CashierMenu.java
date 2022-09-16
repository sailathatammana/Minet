package cashier;

import data.OrderListPool;
import userRoles.Cashier;
import utils.OrderList;
import utils.User;

import java.util.List;

public class CashierMenu {
    CashierMenuController controller = new CashierMenuController();

    public void start(User user) {
        List<OrderList> orderLists = OrderListPool.getAllOrderLists();
        Cashier cashier = new Cashier(user, orderLists);
        while (true) {
            String cashierAction = controller.run();
            if (controller.model.menuOptions.contains(cashierAction)) {
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
}
