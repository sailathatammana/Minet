package viewOrderList;

import utils.OrderList;

import java.util.List;

public class ViewOrderListModel {

    private final List<OrderList> orderLists;

    public ViewOrderListModel(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }
}
