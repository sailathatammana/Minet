package userRoles;

import actions.CreateOrder;
import actions.ReturnItem;
import actions.SellItem;
import data.OrderListPool;
import data.TransactionPool;
import utils.Display;
import utils.InventoryItem;
import utils.Transaction;
import utils.User;
import viewOrderList.ViewOrderList;

import java.util.Collections;
import java.util.List;

public class Cashier extends Person implements iCashier {
    private List<Transaction> transactionList;
    User user;

    public Cashier(User user) {
        super();
        this.user = user;
        this.transactionList = TransactionPool.getAllTransactions();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public void sellItem() {
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        SellItem sellItem = new SellItem(user, inventory, transactionList);
        sellItem.sellAnItem();
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
    }

    @Override
    public void createOrder() {
        this.inventory.clear();
        this.getfullInventory();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        CreateOrder createOrder = new CreateOrder(user, inventory, orderLists);
        createOrder.CreateAnOrder();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
    }

    @Override
    public void returnItem() {
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        ReturnItem returnItem = new ReturnItem(user, inventory, transactionList);
        returnItem.ReturnAnItem();
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
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

    @Override
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        new ViewOrderList(orderLists);
        Display.returnMainMenu();
    }

    public InventoryItem getItemByName(String itemName) {
        var isItemAvailable = inventory.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(itemName)).findFirst();
        return isItemAvailable.get();
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

    public boolean validatePositiveQuantity(int requestedQuantity) {
        if (requestedQuantity > 0) {
            return true;
        } else {
            System.out.println("Enter quantity value greater than zero");
        }
        return false;
    }
}