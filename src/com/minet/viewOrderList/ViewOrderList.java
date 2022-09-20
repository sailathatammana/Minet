package com.minet.viewOrderList;

import com.minet.utils.OrderList;

import java.util.List;

public class ViewOrderList {
    public ViewOrderList(List<OrderList> orderLists) {
        ViewOrderListController controller = new ViewOrderListController(orderLists);
        controller.run();
    }
}
