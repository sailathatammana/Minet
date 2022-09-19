package userRoles;

import actions.ReviewOrders;
import actions.ViewOrderedTransactionList;
import data.OrderListPool;
import data.OrderedTransactionListPool;
import utils.Display;
import utils.OrderList;
import utils.User;
import viewOrderList.ViewOrderList;

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
        System.out.println("Ordered Transactions List");
        ViewOrderedTransactionList list = new ViewOrderedTransactionList();
        list.showTable(orderedTransactionList);
        Display.returnMainMenu();
    }
}
