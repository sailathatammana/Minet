package userRoles;

import actions.*;
import data.OrderListPool;
import data.OrderedTransactionListPool;
import data.TransactionPool;
import utils.Display;
import utils.OrderList;
import utils.Transaction;
import utils.User;
import viewOrderList.ViewOrderList;

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
    public void viewOrderList() {
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        this.inventory.clear();
        this.getfullInventory();
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
        new ViewOrderList(orderLists);
        ReviewOrders reviewOrders = new ReviewOrders(inventory, orderLists, orderedTransactionList);
        reviewOrders.reviewOrderList();
        this.orderLists.clear();
        this.orderLists = OrderListPool.getAllOrderLists();
        this.inventory.clear();
        this.getfullInventory();
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
    }

    @Override
    public void viewOrderedTransactionList() {
        this.orderedTransactionList.clear();
        this.orderedTransactionList = OrderedTransactionListPool.getAllOrderedTransactions();
        ViewOrderedTransactionList list = new ViewOrderedTransactionList();
        list.showTable(orderedTransactionList);
        Display.returnMainMenu();
    }
}
