package com.minet.userRoles;

import com.minet.actions.ReviewOrders;
import com.minet.actions.ViewOrderedTransactionList;
import com.minet.data.InventoryPool;
import com.minet.data.OrderListPool;
import com.minet.data.OrderedTransactionListPool;
import com.minet.utils.Display;
import com.minet.utils.OrderList;
import com.minet.utils.User;
import com.minet.viewOrderList.ViewOrderList;

import java.util.List;

public class Manager extends Person implements iManager {
    User user;
    private List<OrderList> orderedTransactionList;

    public Manager(User user) {
        super();
        this.user = user;
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
    }

    @Override
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
        new ViewOrderList(orderLists);
        ReviewOrders reviewOrders = new ReviewOrders(inventory, orderLists, orderedTransactionList);
        reviewOrders.reviewOrderList();
    }

    @Override
    public void viewOrderedTransactionList() {
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
        System.out.println("Ordered Transactions List");
        ViewOrderedTransactionList list = new ViewOrderedTransactionList();
        list.showTable(orderedTransactionList);
        Display.returnMainMenu();
    }
}
