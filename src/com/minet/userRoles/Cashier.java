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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public void sellItem() {
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        SellItem sellItem = new SellItem(user, inventory, transactionList);
        sellItem.sellAnItem();
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
    }

    @Override
    public void createOrder() {
        this.inventory.clear();
        this.getfullInventory();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        CreateOrder createOrder = new CreateOrder(user, inventory, orderLists);
        createOrder.CreateAnOrder();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
    }

    @Override
    public void returnItem() {
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
        ReturnItem returnItem = new ReturnItem(user, inventory, transactionList);
        returnItem.ReturnAnItem();
        this.inventory.clear();
        this.getfullInventory();
        this.transactionList.clear();
        this.transactionList = TransactionPool.getAllTransactions();
    }


    @Override
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        new ViewOrderList(orderLists);
        Display.returnMainMenu();
    }

}