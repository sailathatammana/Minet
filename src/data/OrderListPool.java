package data;

import utils.FileHandler;
import utils.InventoryItem;
import utils.OrderList;
import utils.OrderStatusType;

import java.util.ArrayList;
import java.util.List;

public class OrderListPool {
    static FileHandler<OrderList> fileHandler = new FileHandler<>();
    private static List<OrderList> orderLists = new ArrayList<OrderList>();


    public static List<OrderList> getAllOrderLists() {
        List<List<String>> result = fileHandler.readFromFile("assets/orderlist.txt");
        result.forEach(item -> {
            int orderId = Integer.parseInt(item.get(0));
            int id = Integer.parseInt(item.get(1));
            String title = item.get(2);
            String description = item.get(3);
            float price = Float.parseFloat(item.get(4));
            int quantity = Integer.parseInt(item.get(5));
            String cashierName = item.get(6);
            OrderStatusType orderStatus = OrderStatusType.valueOf(item.get(7));
            orderLists.add(new OrderList(orderId, (new InventoryItem(id, title, description, price, quantity)), cashierName, orderStatus));
        });
        return orderLists;
    }
}
