package com.minet.actions;

import com.minet.utils.Display;
import com.minet.utils.Table;
import com.minet.utils.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ViewTransactions {
    public ViewTransactions(List<Transaction> transactionList) {
        showTable(transactionList);
    }

    public void showTable(List<Transaction> transactionList) {
        Display.clearScreen();
        System.out.println("Ordered Transactions List");
        List<Integer> columnWidths = List.of(10, 15, 5, 15, 7, 15);
        List<String> headers = List.of("Receipt ID", "Product", "Qty", "Cashier Name", "Price", "Transact type");
        List<List<String>> body = parseData(transactionList);
        Table table = new Table(columnWidths, headers, body);
        table.showData();
    }

    private List<List<String>> parseData(List<Transaction> transactionList) {
        List<List<String>> result = new ArrayList<>();
        transactionList.forEach(item -> {
            String receiptNumber = String.valueOf(item.getReceiptNumber());
            String title = item.getItemName();
            String quantity = String.valueOf(item.getItemQuantity());
            String cashierName = String.valueOf(item.getCashierName());
            String price = String.valueOf(item.getAmount());
            String transactionType = String.valueOf(item.getTransactionType());
            List<String> data = List.of(receiptNumber, title, quantity, cashierName, price, transactionType);
            result.add(data);
        });
        return result;
    }
}
