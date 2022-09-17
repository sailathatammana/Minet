package userRoles;

import utils.Display;
import utils.FileHandler;
import utils.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class Person {
    protected List<InventoryItem> inventory = new ArrayList<InventoryItem>();
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();

    public Person() {
        getInventory();
    }

    public void viewInventory() {
        displayInventory();
        Display.returnMainMenu();
    }

    public void getInventory() {
        List<List<String>> result = fileHandler.readFromFile("assets/inventory.txt");
        for (List<String> strings : result) {
            int id = Integer.parseInt(strings.get(0));
            String title = strings.get(1);
            String description = strings.get(2);
            float price = Float.parseFloat(strings.get(3));
            int quantity = Integer.parseInt(strings.get(4));
            inventory.add(new InventoryItem(id, title, description, price, quantity));
        }
    }

    public void displayInventory() {
        String tableHeader = "| Id    | Product         | Price   | Qty  | Status        |%n";
        String tableBorder = "+-------+-----------------+---------+------+---------------+%n";
        String tableFormat = "| %-5d | %-15s | %-7.2f | %-4d | %-13s |%n";
        System.out.format(tableBorder);
        System.out.format(tableHeader);
        System.out.format(tableBorder);
        for (InventoryItem inventoryItem : inventory) {
            int id = inventoryItem.getId();
            String title = inventoryItem.getTitle();
            float price = inventoryItem.getPrice();
            int quantity = inventoryItem.getQuantity();
            String stockStatus = inventoryItem.getStockStatus();
            System.out.format(tableFormat, id, title, price, quantity, stockStatus);
        }
        System.out.format(tableBorder);
    }
}
