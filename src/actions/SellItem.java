package actions;

import utils.*;

import java.util.List;
import java.util.NoSuchElementException;

public class SellItem {
    private final List<InventoryItem> inventory;
    private final List<Transaction> transactionList;
    User user;
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    FileHandler<Transaction> transactionFileHandler = new FileHandler<>();

    public SellItem(User user, List<InventoryItem> inventory, List<Transaction> transactionList) {
        this.user = user;
        this.inventory = inventory;
        this.transactionList = transactionList;
    }

    public void sellAnItem() {
        while (true) {
            InventoryItem item;
            try {
                String itemName = Display.printHeader("Item name");
                if (Display.checkInput(itemName)) return;
                String input = Display.printHeader("Quantity");
                if (Display.checkInput(input)) return;
                int requestedQuantity = Integer.parseInt(input);
                item = getItemByName(itemName);
                if (addItemToTransaction(item, requestedQuantity)) break;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a number for quantity ");
            } catch (NoSuchElementException e) {
                System.out.println("Item unavailable");
            }
        }
        transactionFileHandler.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        Display.returnMainMenu();
    }

    public InventoryItem getItemByName(String itemName) {
        var isItemAvailable = inventory.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(itemName)).findFirst();
        return isItemAvailable.get();
    }

    public boolean addItemToTransaction(InventoryItem item, int requestedQuantity) {
        if (validateQuantity(requestedQuantity, item.getQuantity())) {
            int updatedQuantity = item.getQuantity() - requestedQuantity;
            item.setQuantity(updatedQuantity);
            String itemTitle = item.getTitle();
            String cashierName = user.getFullName();
            int receiptNumber = RandomGenerator.generateRandomNumber(9999999);
            float totalCost = requestedQuantity * item.getPrice();
            TransactionType type = TransactionType.SELL;
            System.out.println("Your receipt number is: " + receiptNumber);
            transactionList.add(new Transaction(itemTitle, requestedQuantity, cashierName, receiptNumber, totalCost, type));
            return true;
        }
        return false;
    }

    public boolean validateQuantity(int requestedQuantity, int itemQuantity) {
        if ((itemQuantity - requestedQuantity >= 1) && requestedQuantity <= itemQuantity && requestedQuantity > 0) {
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
