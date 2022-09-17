package userRoles;

import utils.*;
import viewOrderList.ViewOrderList;

import java.util.*;

public class Cashier extends Person implements iCashier {
    private List<Transaction> transactionList = new ArrayList<Transaction>();
    FileHandler<Transaction> transactionFileHandler = new FileHandler<>();
    FileHandler<OrderList> orderListFileHandlerFileHandler = new FileHandler<>();
    Scanner scanner = new Scanner(System.in);
    User user;

    public Cashier(User user) {
        super();
        this.user = user;
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
                if (Display.checkInput(input)) {
                    Display.clearScreen();
                    return;
                }
                int requestedQuantity = Integer.parseInt(input);
                item = getItemByName(itemName);
                if (item != null && validateQuantity(requestedQuantity, item.getQuantity())) {
                    int updatedQuantity = item.getQuantity() - requestedQuantity;
                    item.setQuantity(updatedQuantity);
                    String itemTitle = item.getTitle();
                    String cashierName = user.getFullName();
                    int receiptNumber = RandomGenerator.generateRandomNumber(9999999);
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
                if (item != null && validatePositiveQuantity(requestedQuantity)) {
                    if (validateItemInPool(itemName, requestedQuantity)) break;
                    int orderId = RandomGenerator.generateRandomNumber(9999);
                    int id = item.getId();
                    String itemTitle = item.getTitle();
                    String description = item.getDescription();
                    float price = item.getPrice();
                    String cashierName = user.getFullName();
                    OrderStatusType orderStatus = OrderStatusType.PENDING;
                    orderLists.add(new OrderList(orderId, (new InventoryItem(id, itemTitle, description, price, requestedQuantity)), cashierName, orderStatus));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for quantity ");
            }
        }
        orderListFileHandlerFileHandler.writeToFile(orderLists, "assets/orderlist.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        Display.returnMainMenu();
    }

    @Override
    public void returnItem() {
        while (true) {
            try {
                Transaction transactionItem;
                System.out.print("Enter Receipt Number/Enter `q` to go back to main menu\nInput:");
                String input = scanner.nextLine();
                if (Display.checkInput(input)) return;
                int returnReceiptNumber = Integer.parseInt(input);
                transactionItem = getItemByReceiptNumber(returnReceiptNumber);
                if (transactionItem != null) {
                    String returnedItemName = transactionItem.getItemName();
                    int returnedItemQuantity = transactionItem.getItemQuantity();
                    for (InventoryItem inventoryItem : inventory) {
                        if (Objects.equals(inventoryItem.getTitle(), returnedItemName)) {
                            int updateQuantity = inventoryItem.getQuantity() + returnedItemQuantity;
                            inventoryItem.setQuantity(updateQuantity);
                        }
                    }
                    String itemTitle = transactionItem.getItemName();
                    String cashierName = user.getFullName();
                    float totalCost = transactionItem.getAmount();
                    TransactionType type = TransactionType.RETURN;
                    transactionList.add(new Transaction(itemTitle, returnedItemQuantity, cashierName, returnReceiptNumber, totalCost, type));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
        transactionFileHandler.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        Display.returnMainMenu();
    }

    private Transaction getItemByReceiptNumber(int returnReceiptNumber) {
        List<Integer> receiptNumberList = new ArrayList<>();
        for (Transaction transactionItem : transactionList) {
            receiptNumberList.add(transactionItem.getReceiptNumber());
        }
        int occurrences = Collections.frequency(receiptNumberList, returnReceiptNumber);
        if (occurrences > 1) {
            System.out.println("This item is already returned");
            return null;
        } else if (occurrences == 1) {
            for (Transaction transactionItem : transactionList) {
                if (transactionItem.getReceiptNumber() == returnReceiptNumber) {
                    return transactionItem;
                }
            }
        }
        System.out.println("Invalid Receipt number");
        return null;
    }

    @Override
    public void viewOrderList() {
        new ViewOrderList(orderLists);
        Display.returnMainMenu();
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

    public InventoryItem getItemByName(String itemName) {
        for (InventoryItem inventoryItem : inventory) {
            if (inventoryItem.getTitle().equalsIgnoreCase(itemName)) {
                return inventoryItem;
            }
        }
        System.out.println("Item unavailable");
        return null;
    }

    public boolean validateItemInPool(String itemName, int requestedQuantity) {
        int matchItemIndex = -80;
        for (OrderList orderList : orderLists) {
            if (orderList.getItem().getTitle().equalsIgnoreCase(itemName) && (Objects.equals(orderList.getCashierName(), user.getFullName()))) {
                matchItemIndex = orderLists.indexOf(orderList);
            }
        }
        if (matchItemIndex == -80) {
            return false;
        } else {
            int updatedQuantity = orderLists.get(matchItemIndex).getItem().getQuantity() + requestedQuantity;
            orderLists.get(matchItemIndex).getItem().setQuantity(updatedQuantity);
            return true;
        }
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