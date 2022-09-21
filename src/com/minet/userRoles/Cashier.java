package com.minet.userRoles;

import com.minet.actions.CreateOrder;
import com.minet.actions.ReturnItem;
import com.minet.actions.SellItem;
import com.minet.data.OrderListPool;
import com.minet.data.TransactionPool;
import com.minet.utils.Display;
import com.minet.utils.Transaction;
import com.minet.utils.User;
import com.minet.viewOrderList.ViewOrderList;

import java.util.List;

public class Cashier extends Person implements iCashier {
    private List<Transaction> transactionList;
    User user;

    public Cashier(User user) {
        super();
        this.user = user;
        this.transactionList = TransactionPool.getAllTransactions();
    }

    @Override
    public void sellItem() {
        this.resyncInventory();
        resyncTransactionList();
        SellItem sellItem = new SellItem(user, inventory, transactionList);
        sellItem.sellAnItem();
    }

    @Override
    public void createOrder() {
        this.resyncInventory();
        resyncOrderList();
        CreateOrder createOrder = new CreateOrder(user, inventory, orderLists);
        createOrder.CreateAnOrder();
    }

    @Override
    public void returnItem() {
        this.resyncInventory();
        resyncTransactionList();
        ReturnItem returnItem = new ReturnItem(user, inventory, transactionList);
        returnItem.ReturnAnItem();
    }

    @Override
    public void viewOrderList() {
        resyncOrderList();
        new ViewOrderList(orderLists);
        Display.returnMainMenu();
    }

    private void resyncTransactionList() {
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
    }

    private void resyncOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
    }
}