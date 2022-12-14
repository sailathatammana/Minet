package com.minet.data;

import com.minet.utils.FileHandler;
import com.minet.utils.InventoryItem;
import com.minet.utils.OrderList;
import com.minet.utils.OrderStatusType;

import java.util.ArrayList;
import java.util.List;

public class OrderedTransactionListPool {
    static FileHandler<OrderList> fileHandler = new FileHandler<>();
    private static final List<OrderList> orderedTransactionList = new ArrayList<>();

    public static List<OrderList> getAllOrderedTransactions() {
        List<List<String>> result = fileHandler.readFromFile("assets/OrderedTransactionList.txt");
        result.forEach(item -> {
            int orderId = Integer.parseInt(item.get(0));
            int id = Integer.parseInt(item.get(1));
            String title = item.get(2);
            String description = item.get(3);
            float sellingPrice = Float.parseFloat(item.get(4));
            float costPrice = Float.parseFloat(item.get(5));
            int quantity = Integer.parseInt(item.get(6));
            String cashierName = item.get(7);
            OrderStatusType orderStatus = OrderStatusType.valueOf(item.get(8));
            orderedTransactionList.add(new OrderList(orderId, (new InventoryItem(id, title, description, sellingPrice, costPrice, quantity)), cashierName, orderStatus));
        });
        return orderedTransactionList;
    }
}
