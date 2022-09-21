package com.minet.userRoles;

import com.minet.actions.ViewInventory;
import com.minet.data.InventoryPool;
import com.minet.data.OrderListPool;
import com.minet.utils.Display;
import com.minet.utils.InventoryItem;
import com.minet.utils.OrderList;

import java.util.List;

public class Person {
    protected List<InventoryItem> inventory;
    protected List<OrderList> orderLists;


    public Person() {
        this.inventory = InventoryPool.getfullInventory();
        this.orderLists = OrderListPool.getAllOrderLists();
    }

    public void viewInventory() {
        resyncInventory();
        new ViewInventory(inventory);
        Display.returnMainMenu();
    }

    protected void resyncInventory() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
    }
}
