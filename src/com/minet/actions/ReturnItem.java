package com.minet.actions;

import com.minet.utils.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReturnItem {
    private final List<InventoryItem> inventory;
    private final List<Transaction> transactionList;
    User user;
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    FileHandler<Transaction> transactionFileHandler = new FileHandler<>();

    public ReturnItem(User user, List<InventoryItem> inventory, List<Transaction> transactionList) {
        this.user = user;
        this.inventory = inventory;
        this.transactionList = transactionList;
    }

    public void ReturnAnItem() {
        Display.clearScreen();
        while (true) {
            try {
                Transaction transactionItem;
                String input = Display.printHeader("Receipt Number");
                if (Display.checkInput(input)) return;
                int returnReceiptNumber = Integer.parseInt(input);
                transactionItem = getItemByReceiptNumber(returnReceiptNumber);
                if (addReturnItem(transactionItem, returnReceiptNumber)) break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
        transactionFileHandler.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        Display.returnMainMenu();
    }

    public Transaction getItemByReceiptNumber(int returnReceiptNumber) {
        List<Integer> receiptNumberList = transactionList.stream()
                .map(Transaction::getReceiptNumber)
                .toList();
        int occurrences = Collections.frequency(receiptNumberList, returnReceiptNumber);
        if (occurrences > 1) {
            System.out.println("This item is already returned");
            return null;
        } else if (occurrences == 1) {
            var isReceiptNumberAvailable = transactionList.stream()
                    .filter(item -> item.getReceiptNumber() == returnReceiptNumber).findFirst();
            return isReceiptNumberAvailable.get();
        }
        System.out.println("Invalid Receipt number");
        return null;
    }

    public boolean addReturnItem(Transaction transactionItem, int returnReceiptNumber) {
        if (transactionItem != null) {
            String returnedItemName = transactionItem.getItemName();
            int returnedItemQuantity = transactionItem.getItemQuantity();
            var inventoryItem = inventory.stream()
                    .filter(item -> Objects.equals(item.getTitle(), returnedItemName)).findFirst();
            inventoryItem.get().setQuantity(inventoryItem.get().getQuantity() + returnedItemQuantity);
            String itemTitle = transactionItem.getItemName();
            String cashierName = user.getFullName();
            float totalCost = transactionItem.getAmount();
            TransactionType type = TransactionType.RETURN;
            System.out.println("Your item with receipt number " + returnReceiptNumber + " is returned.");
            transactionList.add(new Transaction(itemTitle, returnedItemQuantity, cashierName, returnReceiptNumber, totalCost, type));
            return true;
        }
        return false;
    }
}
