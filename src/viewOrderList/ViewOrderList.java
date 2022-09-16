package viewOrderList;

import utils.OrderList;

import java.util.List;

public class ViewOrderList {
    public ViewOrderList(List<OrderList> orderLists) {
        ViewOrderListController controller = new ViewOrderListController(orderLists);
        controller.run();
    }
}
