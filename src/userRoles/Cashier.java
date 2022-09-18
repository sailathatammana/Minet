package userRoles;

import data.OrderListPool;
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

    public List<Transaction> getTransactionList() {
        return transactionList;
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
                if (validateQuantity(requestedQuantity, item.getQuantity())) {
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
            } catch (NoSuchElementException e) {
                System.out.println("Item unavailable");
            }
        }
        transactionFileHandler.writeToFile(transactionList, "assets/transactions.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        this.inventory.clear();
        this.getfullInventory();
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
                if (validatePositiveQuantity(requestedQuantity)) {
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
            } catch (NoSuchElementException e) {
                System.out.println("Item unavailable");
            }
        }
        orderListFileHandlerFileHandler.writeToFile(orderLists, "assets/orderlist.txt");
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        //fileHandler.writeToFile(inventory, "assets/inventory.txt");
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
                    var inventoryItem = inventory.stream()
                            .filter(item -> Objects.equals(item.getTitle(), returnedItemName)).findFirst();
                    inventoryItem.get().setQuantity(inventoryItem.get().getQuantity() + returnedItemQuantity);
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
        this.inventory.clear();
        this.getfullInventory();
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

    @Override
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
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
        var isItemAvailable = inventory.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(itemName)).findFirst();
        return isItemAvailable.get();
    }

    public boolean validateItemInPool(String itemName, int requestedQuantity) {
        var matchItem = orderLists.stream()
                .filter(item -> item.getItem().getTitle().equalsIgnoreCase(itemName) && (Objects.equals(item.getCashierName(), user.getFullName())))
                .findFirst();
        boolean check = matchItem.isPresent();
        matchItem.ifPresent(item -> {
            int matchItemIndex = orderLists.indexOf(matchItem.get());
            int updatedQuantity = orderLists.get(matchItemIndex).getItem().getQuantity() + requestedQuantity;
            orderLists.get(matchItemIndex).getItem().setQuantity(updatedQuantity);
        });
        return check;
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