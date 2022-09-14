package userRoles;

import utils.FileHandler;
import utils.InventoryItem;
import utils.User;

import java.util.ArrayList;
import java.util.List;

public class Cashier implements iCashier {
    List<InventoryItem> inventory = new ArrayList<InventoryItem>();
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    User user;

    public Cashier(User user) {
        this.user = user;
        inventory = getInventory();
    }

    @Override
    public void sellItem() {
        System.out.println(user.getFullName());
        System.out.println("Sell Item");
    }

    @Override
    public void createOrder() {
        System.out.println("Create Order");
    }

    @Override
    public void returnItem() {
        System.out.println("Return Item");
    }

    @Override
    public void viewInventory() {
        displayInventory();
    }

    public List<InventoryItem> getInventory() {
        List<List<String>> result = fileHandler.readFromFile("assets/inventory.txt");
        for (List<String> strings : result) {
            int id = Integer.parseInt(strings.get(0));
            String title = strings.get(1);
            String description = strings.get(2);
            float price = Float.parseFloat(strings.get(3));
            int quantity = Integer.parseInt(strings.get(4));
            inventory.add(new InventoryItem(id, title, description, price, quantity));
        }
        return inventory;
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
            System.out.format(tableFormat, id, title, price, quantity,stockStatus);
        }
        System.out.format(tableBorder);
    }
}
