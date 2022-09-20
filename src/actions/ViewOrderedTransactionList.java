package actions;

import utils.Display;
import utils.OrderList;
import utils.Table;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderedTransactionList {
    public void showTable(List<OrderList> orderedTransactionList) {
        Display.clearScreen();
        System.out.println("Ordered Transactions List");
        List<Integer> columnWidths = List.of(10, 15, 4, 15, 10);
        List<String> headers = List.of("Order ID", "Product", "Qty", "CashierName", "Status");
        List<List<String>> body = parseOrder(orderedTransactionList);
        Table table = new Table(columnWidths, headers, body);
        table.showData();
    }

    private List<List<String>> parseOrder(List<OrderList> orderedTransactionList) {
        List<List<String>> result = new ArrayList<>();
        orderedTransactionList.forEach(item -> {
            String index = String.valueOf(item.getOrderId());
            String title = item.getItem().getTitle();
            String quantity = String.valueOf(item.getItem().getQuantity());
            String cashierName = item.getCashierName();
            String orderStatus = String.valueOf(item.getOrderStatus());
            List<String> data = List.of(index, title, quantity, cashierName, orderStatus);
            result.add(data);
        });
        return result;
    }
}
