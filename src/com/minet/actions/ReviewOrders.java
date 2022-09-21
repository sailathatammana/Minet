package com.minet.actions;

import com.minet.utils.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ReviewOrders {
    private final List<InventoryItem> inventory;
    private final List<OrderList> orderLists;
    private final List<OrderList> orderedTransactionList;
    FileHandler<InventoryItem> fileHandler = new FileHandler<>();
    FileHandler<OrderList> orderListFileHandler = new FileHandler<>();
    FileHandler<OrderList> orderedTransactionListFileHandler = new FileHandler<>();

    public ReviewOrders(List<InventoryItem> inventory, List<OrderList> orderLists, List<OrderList> orderedTransactionList) {
        this.inventory = inventory;
        this.orderLists = orderLists;
        this.orderedTransactionList = orderedTransactionList;
    }

    public void reviewOrderList() {
        OrderList item;
        while (true) {
            try {
                String input = Display.printHeader("OrderId number");
                if (Display.checkInput(input)) return;
                int requestedOrderId = Integer.parseInt(input);
                item = getItemById(requestedOrderId);
                String confirmOrder = Display.printHeader("yes to approve and No to reject");
                if (Display.checkInput(confirmOrder)) return;
                if (addTransaction(item, confirmOrder)) break;
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

    public boolean addTransaction(OrderList item, String confirmOrder) {
        if ((Objects.equals(confirmOrder.toLowerCase(), "yes")) || (Objects.equals(confirmOrder.toLowerCase(), "no"))) {
            String itemName = item.getItem().getTitle();
            int itemQuantity = item.getItem().getQuantity();
            int id = item.getItem().getId();
            String description = item.getItem().getDescription();
            float sellingPrice = item.getItem().getSellingPrice();
            float costPrice = item.getItem().getCostPrice();
            setStatus(item, confirmOrder, itemName, itemQuantity);
            orderLists.remove(item);
            orderedTransactionList.add(new OrderList(item.getOrderId(), (new InventoryItem(id, itemName,
                    description, sellingPrice, costPrice, itemQuantity)), item.getCashierName(), item.getOrderStatus()));
            return true;
        } else {
            System.out.println("You entered wrong input");
            return false;
        }
    }

    private void setStatus(OrderList item, String confirmOrder, String itemName, int itemQuantity) {
        if ((Objects.equals(confirmOrder.toLowerCase(), "yes"))) {
            item.setOrderStatus(OrderStatusType.APPROVED);
            var newItem = inventory.stream().filter(x -> x.getTitle().equals(itemName)).findFirst();
            newItem.ifPresent(value -> newItem.get().setQuantity(newItem.get().getQuantity() + itemQuantity));
            System.out.println("Order " + item.getOrderId() + " is approved and updating the inventory");
        } else {
            item.setOrderStatus(OrderStatusType.REJECTED);
            System.out.println("Order " + item.getOrderId() + " is rejected");
        }
    }
}
