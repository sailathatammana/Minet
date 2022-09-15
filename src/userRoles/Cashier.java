package userRoles;

import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cashier implements iCashier {
    //InventoryItem item = null;
    private List<InventoryItem> inventory = new ArrayList<InventoryItem>();
    private List<Transaction> transactionList = new ArrayList<Transaction>();
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    FileHandler<Transaction> transactionFileHandler = new FileHandler<>();
    Scanner scanner = new Scanner(System.in);
    User user;

    public Cashier(User user) {
        this.user = user;
        getInventory();
        getAllTransactions();
    }

    @Override
    public void sellItem() {
        while (true) {
            InventoryItem item;
            try {
                System.out.print("Enter Item name/Enter `q` to go back to main menu\nInput:");
                String itemName = scanner.nextLine();
                if (Display.checkInput(itemName)) return;
                System.out.print("Enter Quantity/Enter `q` to go back to main menu\nInput: ");
                String input = scanner.nextLine();
                if (Display.checkInput(input)) return;
                int requestedQuantity = Integer.parseInt(input);
                item = getItemByName(itemName);
                if (item != null && validateQuantity(requestedQuantity, item.getQuantity())) {
                    int updatedQuantity = item.getQuantity() - requestedQuantity;
                    item.setQuantity(updatedQuantity);
                    String itemTitle = item.getTitle();
                    String cashierName = user.getFullName();
                    int receiptNumber = GenerateReceiptNumber.generateReceiptNumber();
                    float totalCost = requestedQuantity * item.getPrice();
                    TransactionType type = TransactionType.SELL;
                    transactionList.add(new Transaction(itemTitle, requestedQuantity, cashierName, receiptNumber, totalCost, type));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for quantity ");
            }
        }
        transactionFileHandler.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        Display.returnMainMenu();
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

    public void getAllTransactions() {
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

    public InventoryItem getItemByName(String itemName) {
        for (InventoryItem inventoryItem : inventory) {
            if (inventoryItem.getTitle().equalsIgnoreCase(itemName)) {
                return inventoryItem;
            }
        }
        System.out.println("Item unavailable");
        return null;
    }

    public boolean validateQuantity(int requestedQuantity, int itemQuantity) {
        if (itemQuantity > 1 && requestedQuantity <= itemQuantity && requestedQuantity > 0) {
            return true;
        } else if (requestedQuantity <= 0) {
            System.out.println("Enter quantity value greater than zero");
        } else if (itemQuantity <= 1) {
            System.out.println("Out of stock");
        } else {
            System.out.println("Insufficient quantity to sell");
        }
        return false;
    }
}
