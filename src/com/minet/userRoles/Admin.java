package com.minet.userRoles;

import com.minet.actions.*;
import com.minet.data.InventoryPool;
import com.minet.data.OrderListPool;
import com.minet.data.OrderedTransactionListPool;
import com.minet.data.TransactionPool;
import com.minet.utils.Display;
import com.minet.utils.OrderList;
import com.minet.utils.Transaction;
import com.minet.utils.User;
import com.minet.viewOrderList.ViewOrderList;

import java.util.List;

public class Admin extends Person implements iAdmin {
    User user;
    private List<Transaction> transactionList;
    private List<OrderList> orderedTransactionList;

    public Admin(User user) {
        this.user = user;
        this.transactionList = TransactionPool.getAllTransactions();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
    }

    @Override
    public void sellItem() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        SellItem sellItem = new SellItem(user, inventory, transactionList);
        sellItem.sellAnItem();
    }

    @Override
    public void returnItem() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        ReturnItem returnItem = new ReturnItem(user, inventory, transactionList);
        returnItem.ReturnAnItem();
    }

    @Override
    public void createOrder() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        CreateOrder createOrder = new CreateOrder(user, inventory, orderLists);
        createOrder.CreateAnOrder();
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
        ViewOrderedTransactionList list = new ViewOrderedTransactionList();
        list.showTable(orderedTransactionList);
        Display.returnMainMenu();
    }


    @Override
    public void getStatistics() {
        this.inventory.clear();
        this.inventory = InventoryPool.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        Statistics statistics = new Statistics(inventory, transactionList);
        statistics.generateStatistics();
    }
}
