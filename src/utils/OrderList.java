package utils;

import java.io.Serializable;

public class OrderList implements Serializable {

    private InventoryItem item;
    private String cashierName;

    public OrderList(InventoryItem item, String cashierName) {
        this.item = item;
        this.cashierName = cashierName;
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

    @Override
    public String toString() {
        return item + "," + cashierName;
    }
}
