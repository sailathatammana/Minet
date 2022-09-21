package com.minet.userRoles;

import com.minet.data.InventoryPool;
import com.minet.data.OrderListPool;
import com.minet.utils.Display;
import com.minet.utils.InventoryItem;
import com.minet.utils.OrderList;

import java.util.List;

public class Person {
    protected List<InventoryItem> inventory;
    protected List<OrderList> orderLists;


    public Person() {
        this.inventory = InventoryPool.getfullInventory();
        this.orderLists = OrderListPool.getAllOrderLists();
    }
    
    public void viewInventory() {
        resyncInventory();
        displayInventory();
        Display.returnMainMenu();
    }

    protected void resyncInventory() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
    }

    public void displayInventory() {
        Display.clearScreen();
        System.out.println("Inventory List");
        String tableHeader = "| Id    | Product         | Price   | Qty  | Status        |%n";
        String tableBorder = "+-------+-----------------+---------+------+---------------+%n";
        String tableFormat = "| %-5d | %-15s | %-7.2f | %-4d | %-13s |%n";
        System.out.format(tableBorder);
        System.out.format(tableHeader);
        System.out.format(tableBorder);
        inventory.forEach(item -> {
            int id = item.getId();
            String title = item.getTitle();
            float price = item.getSellingPrice();
            int quantity = item.getQuantity();
            String stockStatus = item.getStockStatus();
            System.out.format(tableFormat, id, title, price, quantity, stockStatus);
        });
        System.out.format(tableBorder);
    }
}
