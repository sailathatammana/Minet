package viewOrderList;

import utils.OrderList;
import utils.Table;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderListView {
    public void showData(List<OrderList> orderLists) {
        System.out.println("OrderList Table");
        showTable(orderLists);
    }

    private void showTable(List<OrderList> orderLists) {
        List<Integer> columnWidths = List.of(15, 4, 15, 10);
        List<String> headers = List.of("Product", "Qty", "CashierName", "Status");
        List<List<String>> body = parseOrder(orderLists);
        Table table = new Table(columnWidths, headers, body);
        table.showData();
    }

    private List<List<String>> parseOrder(List<OrderList> orderLists) {
        List<List<String>> result = new ArrayList<>();
        for (OrderList item : orderLists) {
            String title = item.getItem().getTitle();
            String quantity = String.valueOf(item.getItem().getQuantity());
            String cashierName = item.getCashierName();
            String orderStatus = String.valueOf(item.getOrderStatus());
            List<String> data = List.of(title, quantity, cashierName, orderStatus);

            result.add(data);
        }

        return result;
    }
}
