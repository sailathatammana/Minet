package viewOrderList;

import utils.OrderList;

import java.util.List;

public class ViewOrderListController {
    ViewOrderListModel model;
    ViewOrderListView view = new ViewOrderListView();

    public ViewOrderListController(List<OrderList> orderLists) {
        model = new ViewOrderListModel(orderLists);
    }

    public void run() {

        view.showData(model.getOrderLists());

    }
}
