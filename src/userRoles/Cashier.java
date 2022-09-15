package userRoles;

import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cashier implements iCashier {
    InventoryItem item = null;
    private List<InventoryItem> inventory = new ArrayList<InventoryItem>();
    private List<Transaction> transactionList = new ArrayList<Transaction>();
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    FileHandler<Transaction> fileHandler1 = new FileHandler<>();
    Scanner scanner = new Scanner(System.in);
    User user;

    public Cashier(User user) {
        this.user = user;
        inventory = getInventory();
    }

    @Override
    public void sellItem() {
        transactionList = getAllTransactions();
        while (true) {
            boolean done;
            try {
                System.out.print("Enter Item name: ");
                String itemName = scanner.nextLine();
                System.out.print("Enter Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                done = getItemByName(itemName);
                if (done && validateQuantity(quantity, item.getQuantity())) {
                    int updatedQuantity = item.getQuantity();
                    updatedQuantity = updatedQuantity - quantity;
                    item.setQuantity(updatedQuantity);
                    String itemTitle = item.getTitle();
                    String cashierName = user.getFullName();
                    int receiptNumber = GenerateReceiptNumber.generateReceiptNumber();
                    float amount = quantity * item.getPrice();
                    TransactionType type = TransactionType.SELL;
                    transactionList.add(new Transaction(itemTitle, quantity, cashierName, receiptNumber, amount, type));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for quantity ");
            }
        }
        fileHandler1.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
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

    public List<Transaction> getAllTransactions() {
        List<List<String>> result = fileHandler.readFromFile("assets/transactions.txt");
        for (List<String> strings : result) {
            String inventoryItem = strings.get(0);
            int itemQuantity = Integer.parseInt(strings.get(1));
            String cashierName = strings.get(2);
            int receiptNumber = Integer.parseInt(strings.get(3));
            float amount = Float.parseFloat(strings.get(4));
            TransactionType type = TransactionType.valueOf(strings.get(5));
            transactionList.add(new Transaction(inventoryItem, itemQuantity, cashierName, receiptNumber, amount, type));
        }
        return transactionList;
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

    public boolean getItemByName(String itemName) {
        InventoryItem item2;
        for (InventoryItem inventoryItem : inventory) {
            String name = inventoryItem.getTitle();
            if (name.equalsIgnoreCase(itemName)) {
                item2 = inventoryItem;
                item = item2;
                return true;
            }
        }
        System.out.println("Item unavailable");
        return false;
    }

    public boolean validateQuantity(int quantity, int itemQuantity) {
        if (itemQuantity > 1 && quantity <= itemQuantity && quantity > 0) {
            return true;
        } else if (itemQuantity <= 1) {
            System.out.println("Out of stock");
        } else if (quantity < 0) {
            System.out.println("Enter quantity value greater than zero");
        } else {
            System.out.println("Insufficient quantity to sell");
        }
        return false;
    }
}
