package userRoles;

import data.OrderListPool;
import utils.Display;
import utils.FileHandler;
import utils.InventoryItem;
import utils.OrderList;

import java.util.ArrayList;
import java.util.List;

public class Person {
    protected List<InventoryItem> inventory = new ArrayList<InventoryItem>();
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    protected List<OrderList> orderLists;


    public Person() {
        getfullInventory();
        this.orderLists = OrderListPool.getAllOrderLists();
    }

    public List<InventoryItem> getInventoryList() {
        return inventory;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void viewInventory() {
        this.inventory.clear();
        this.getfullInventory();
        displayInventory();
        Display.returnMainMenu();
    }


    public void getfullInventory() {
        List<List<String>> result = fileHandler.readFromFile("assets/inventory.txt");
        result.forEach(item -> {
            int id = Integer.parseInt(item.get(0));
            String title = item.get(1);
            String description = item.get(2);
            float sellingPrice = Float.parseFloat(item.get(3));
            float costPrice = Float.parseFloat(item.get(4));
            int quantity = Integer.parseInt(item.get(5));
            inventory.add(new InventoryItem(id, title, description, sellingPrice, costPrice, quantity));
        });
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
