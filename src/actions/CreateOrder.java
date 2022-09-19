package actions;

import utils.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CreateOrder {
    private final List<InventoryItem> inventory;
    private final List<OrderList> orderLists;
    User user;
    FileHandler<OrderList> orderListFileHandlerFileHandler = new FileHandler<>();

    public CreateOrder(User user, List<InventoryItem> inventory, List<OrderList> orderLists) {
        this.user = user;
        this.inventory = inventory;
        this.orderLists = orderLists;
    }

    public void CreateAnOrder() {
        while (true) {
            InventoryItem item;
            try {
                String itemName = Display.printHeader("Item name");
                if (Display.checkInput(itemName)) return;
                String input = Display.printHeader("Quantity");
                if (Display.checkInput(input)) return;
                int requestedQuantity = Integer.parseInt(input);
                item = getItemByName(itemName);
                if (addItemToOrder(item, itemName, requestedQuantity)) break;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a number for quantity ");
            } catch (NoSuchElementException e) {
                System.out.println("Item unavailable");
            }
        }
        orderListFileHandlerFileHandler.writeToFile(orderLists, "assets/orderlist.txt");
        Display.returnMainMenu();
    }

    public InventoryItem getItemByName(String itemName) {
        var isItemAvailable = inventory.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(itemName)).findFirst();
        return isItemAvailable.get();
    }

    private boolean addItemToOrder(InventoryItem item, String itemName, int requestedQuantity) {
        if (validatePositiveQuantity(requestedQuantity)) {
            if (validateItemInPool(itemName, requestedQuantity)) return true;
            int orderId = RandomGenerator.generateRandomNumber(9999);
            int id = item.getId();
            String itemTitle = item.getTitle();
            String description = item.getDescription();
            float price = item.getPrice();
            String cashierName = user.getFullName();
            OrderStatusType orderStatus = OrderStatusType.PENDING;
            orderLists.add(new OrderList(orderId, (new InventoryItem(id, itemTitle, description, price, requestedQuantity)), cashierName, orderStatus));
            return true;
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

    public boolean validateItemInPool(String itemName, int requestedQuantity) {
        var matchItem = orderLists.stream()
                .filter(item -> {
                    boolean cashierName = Objects.equals(item.getCashierName(), user.getFullName());
                    return item.getItem().getTitle().equalsIgnoreCase(itemName) && cashierName;
                })
                .findFirst();
        boolean check = matchItem.isPresent();
        matchItem.ifPresent(item -> {
            int matchItemIndex = orderLists.indexOf(matchItem.get());
            int updatedQuantity = orderLists.get(matchItemIndex).getItem().getQuantity() + requestedQuantity;
            orderLists.get(matchItemIndex).getItem().setQuantity(updatedQuantity);
        });
        return check;
    }
}
