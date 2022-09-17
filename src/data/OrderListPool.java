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
        for (List<String> strings : result) {
            int orderId = Integer.parseInt(strings.get(0));
            int id = Integer.parseInt(strings.get(1));
            String title = strings.get(2);
            String description = strings.get(3);
            float price = Float.parseFloat(strings.get(4));
            int quantity = Integer.parseInt(strings.get(5));
            String cashierName = strings.get(6);
            OrderStatusType orderStatus = OrderStatusType.valueOf(strings.get(7));
            orderLists.add(new OrderList(orderId, (new InventoryItem(id, title, description, price, quantity)), cashierName, orderStatus));
        }
        return orderLists;
    }
}
