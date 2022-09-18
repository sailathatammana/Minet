package userRoles;

import utils.*;
import viewOrderList.ViewOrderList;

import java.util.*;

public class Manager extends Person implements iManager {
    User user;
    private final List<OrderList> orderedTransactionList = new ArrayList<>();
    FileHandler<OrderList> orderListFileHandler = new FileHandler<>();
    FileHandler<OrderList> orderedTransactionListFileHandler = new FileHandler<>();
    Scanner scanner = new Scanner(System.in);

    public Manager(User user) {
        this.user = user;
        getAllOrderedTransactionList();
    }

    @Override
    public void viewOrderList() {
        new ViewOrderList(orderLists);
        OrderList item;
        while (true) {
            try {
                System.out.print("Enter OrderId number/Enter `q` to go back to main menu\nInput:");
                String input = scanner.nextLine();
                if (Display.checkInput(input)) return;
                int requestedOrderId = Integer.parseInt(input);
                item = getItemById(requestedOrderId);
                System.out.print("Enter yes to approve and No to reject/Enter `q` to go back to main menu\nInput:");
                String confirmOrder = scanner.nextLine();
                if (Display.checkInput(confirmOrder)) return;
                if ((Objects.equals(confirmOrder.toLowerCase(), "yes")) || (Objects.equals(confirmOrder.toLowerCase(), "no"))) {
                    String itemName = item.getItem().getTitle();
                    int itemQuantity = item.getItem().getQuantity();
                    int id = item.getItem().getId();
                    String description = item.getItem().getDescription();
                    float price = item.getItem().getPrice();
                    if ((Objects.equals(confirmOrder.toLowerCase(), "yes"))) {
                        item.setOrderStatus(OrderStatusType.APPROVED);
                        var newItem = inventory.stream().filter(x -> x.getTitle().equals(itemName)).findFirst();
                        newItem.ifPresent(value -> newItem.get().setQuantity(newItem.get().getQuantity() + itemQuantity));
                        System.out.println("Order " + item.getOrderId() + " is approved and updating the inventory");
                    } else {
                        item.setOrderStatus(OrderStatusType.REJECTED);
                        System.out.println("Order " + item.getOrderId() + " is rejected");
                    }
                    orderLists.remove(item);
                    orderedTransactionList.add(new OrderList(item.getOrderId(), (new InventoryItem(id, itemName, description, price, itemQuantity)), item.getCashierName(), item.getOrderStatus()));
                    break;
                } else {
                    System.out.println("You entered wrong input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for orderId ");
            } catch (NoSuchElementException e) {
                System.out.println("Invalid Order id");
            }
        }
        orderListFileHandler.writeToFile(orderLists, "assets/orderlist.txt");
        fileHandler.writeToFile(inventory, "assets/inventory.txt");
        orderedTransactionListFileHandler.writeToFile(orderedTransactionList, "assets/OrderedTransactionList.txt");
        Display.returnMainMenu();
    }

    public OrderList getItemById(int requestedOrderId) {
        var isItemAvailable = orderLists.stream()
                .filter(item -> item.getOrderId() == requestedOrderId).findFirst();
        return isItemAvailable.get();
    }

    public void getAllOrderedTransactionList() {
        List<List<String>> result = orderedTransactionListFileHandler.readFromFile("assets/OrderedTransactionList.txt");
        result.forEach(item -> {
            int orderId = Integer.parseInt(item.get(0));
            int id = Integer.parseInt(item.get(1));
            String title = item.get(2);
            String description = item.get(3);
            float price = Float.parseFloat(item.get(4));
            int quantity = Integer.parseInt(item.get(5));
            String cashierName = item.get(6);
            OrderStatusType orderStatus = OrderStatusType.valueOf(item.get(7));
            orderedTransactionList.add(new OrderList(orderId, (new InventoryItem(id, title, description, price, quantity)), cashierName, orderStatus));

        });
    }
}
