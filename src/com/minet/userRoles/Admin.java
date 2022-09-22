package com.minet.userRoles;

import com.minet.actions.*;
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
        super();
        this.user = user;
        this.transactionList = TransactionPool.getAllTransactions();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
    }

    @Override
    public void sellItem() {
        this.resyncInventory();
        resyncTransactionList();
        SellItem sellItem = new SellItem(user, inventory, transactionList);
        sellItem.sellAnItem();
    }

    @Override
    public void returnItem() {
        this.resyncInventory();
        resyncTransactionList();
        ReturnItem returnItem = new ReturnItem(user, inventory, transactionList);
        returnItem.ReturnAnItem();
    }

    @Override
    public void createOrder() {
        this.resyncInventory();
        resyncOrderList();
        CreateOrder createOrder = new CreateOrder(user, inventory, orderLists);
        createOrder.CreateAnOrder();
    }

    public void viewTransactionList() {
        resyncTransactionList();
        new ViewTransactions(transactionList);
        Display.returnMainMenu();
    }

    @Override
    public void viewOrderList() {
        this.resyncInventory();
        resyncOrderList();
        resyncOrderedTransactionList();
        new ViewOrderList(orderLists);
        ReviewOrders reviewOrders = new ReviewOrders(inventory, orderLists, orderedTransactionList);
        reviewOrders.reviewOrderList();
    }

    @Override
    public void viewOrderedTransactionList() {
        resyncOrderedTransactionList();
        new ViewOrderedTransactionList(orderedTransactionList);
        Display.returnMainMenu();
    }

    @Override
    public void getStatistics() {
        this.resyncInventory();
        resyncTransactionList();
        Statistics statistics = new Statistics(inventory, transactionList);
        statistics.generateStatistics();
    }

    private void resyncTransactionList() {
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
    }

    private void resyncOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
    }

    private void resyncOrderedTransactionList() {
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
    }
}
