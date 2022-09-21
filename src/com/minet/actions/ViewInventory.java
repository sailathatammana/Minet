package com.minet.actions;

import com.minet.utils.Display;
import com.minet.utils.InventoryItem;
import com.minet.utils.Table;

import java.util.ArrayList;
import java.util.List;

public class ViewInventory {

    public ViewInventory(List<InventoryItem> inventory) {
        showTable(inventory);
    }

    public void showTable(List<InventoryItem> inventory) {
        Display.clearScreen();
        System.out.println("Ordered Transactions List");
        List<Integer> columnWidths = List.of(5, 15, 6, 4, 15);
        List<String> headers = List.of("ID", "Product", "Price", "Qty", "Status");
        List<List<String>> body = parseOrder(inventory);
        Table table = new Table(columnWidths, headers, body);
        table.showData();
    }

    private List<List<String>> parseOrder(List<InventoryItem> inventory) {
        List<List<String>> result = new ArrayList<>();
        inventory.forEach(item -> {
            String index = String.valueOf(item.getId());
            String title = item.getTitle();
            String price = String.valueOf(item.getSellingPrice());
            String quantity = String.valueOf(item.getQuantity());
            String orderStatus = String.valueOf(item.getStockStatus());
            List<String> data = List.of(index, title, price, quantity, orderStatus);
            result.add(data);
        });
        return result;
    }
}
