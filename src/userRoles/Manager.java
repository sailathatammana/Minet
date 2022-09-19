package userRoles;

import actions.ReviewOrders;
import data.OrderListPool;
import utils.*;
import viewOrderList.ViewOrderList;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person implements iManager {
    User user;
    private final List<OrderList> orderedTransactionList = new ArrayList<>();
    FileHandler<OrderList> orderedTransactionListFileHandler = new FileHandler<>();

    public Manager(User user) {
        this.user = user;
        getAllOrderedTransactionList();
    }

    @Override
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        this.inventory.clear();
        this.getfullInventory();
        this.orderedTransactionList.clear();
        this.getAllOrderedTransactionList();
        new ViewOrderList(orderLists);
        ReviewOrders reviewOrders = new ReviewOrders(inventory, orderLists, orderedTransactionList);
        reviewOrders.reviewOrderList();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        this.inventory.clear();
        this.getfullInventory();
        this.orderedTransactionList.clear();
        this.getAllOrderedTransactionList();
    }

    public void getAllOrderedTransactionList() {
        List<List<String>> result = orderedTransactionListFileHandler.readFromFile("assets/OrderedTransactionList.txt");
        result.forEach(item -> {
            int orderId = Integer.parseInt(item.get(0));
            int id = Integer.parseInt(item.get(1));
            String title = item.get(2);
            String description = item.get(3);
            float price = Float.parseFloat(item.get(4));
            int quantity = Integer.parseInt(item.get(5));
            String cashierName = item.get(6);
            OrderStatusType orderStatus = OrderStatusType.valueOf(item.get(7));
            orderedTransactionList.add(new OrderList(orderId, (new InventoryItem(id, title, description, price, quantity)), cashierName, orderStatus));

        });
    }
}
