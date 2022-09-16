package data;

import utils.FileHandler;
import utils.InventoryItem;
import utils.OrderList;

import java.util.ArrayList;
import java.util.List;

public class OrderListPool {
    static FileHandler<OrderList> fileHandler = new FileHandler<>();
    private static List<OrderList> orderLists = new ArrayList<OrderList>();


    public static List<OrderList> getAllOrderLists() {
        List<List<String>> result = fileHandler.readFromFile("assets/orderlist.txt");
        for (List<String> strings : result) {
            int id = Integer.parseInt(strings.get(0));
            String title = strings.get(1);
            String description = strings.get(2);
            float price = Float.parseFloat(strings.get(3));
            int quantity = Integer.parseInt(strings.get(4));
            String cashierName = strings.get(5);
            orderLists.add(new OrderList(new InventoryItem(id, title, description, price, quantity), cashierName));
        }
        return orderLists;
    }
}
