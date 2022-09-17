package utils;

import java.io.Serializable;

public class OrderList implements Serializable {

    private InventoryItem item;
    private String cashierName;
    private OrderStatusType orderStatus;

    public OrderList(InventoryItem item, String cashierName, OrderStatusType orderStatus) {
        this.item = item;
        this.cashierName = cashierName;
        this.orderStatus = orderStatus;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public OrderStatusType getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusType orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return item + "," + cashierName + "," + orderStatus;
    }
}
