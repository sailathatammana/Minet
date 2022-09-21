package com.minet.actions;

import com.minet.utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Statistics {
    private final List<InventoryItem> inventory;
    private final List<Transaction> transactionList;

    public Statistics(List<InventoryItem> inventory, List<Transaction> transactionList) {
        this.inventory = inventory;
        this.transactionList = transactionList;
    }

    public void generateStatistics() {
        Display.clearScreen();
        List<Integer> totalQuantity = new ArrayList<>();
        List<Float> profit = new ArrayList<>();
        List<Float> outwardCashFlow = new ArrayList<>();
        List<Float> inwardCashFlow = new ArrayList<>();
        for (InventoryItem inventoryItem : inventory) {
            int itemQuantity = 0;
            for (Transaction transaction : transactionList) {
                itemQuantity = getItemQuantity(inventoryItem, itemQuantity, transaction);
            }
            totalQuantity.add(itemQuantity);
            profit.add(itemQuantity * (inventoryItem.getSellingPrice() - inventoryItem.getCostPrice()));
            outwardCashFlow.add(itemQuantity * inventoryItem.getCostPrice());
            inwardCashFlow.add(itemQuantity * inventoryItem.getSellingPrice());
        }
        System.out.println("Statistics: ");
        displayStatistics(totalQuantity, profit);
        getBestSellingProduct(totalQuantity);
        generateCashFlow(outwardCashFlow, inwardCashFlow);
        Display.returnMainMenu();
    }

    private int getItemQuantity(InventoryItem inventoryItem, int itemQuantity, Transaction transaction) {
        if (Objects.equals(transaction.getItemName(), inventoryItem.getTitle())) {
            if (transaction.getTransactionType() == TransactionType.SELL) {
                itemQuantity = itemQuantity + transaction.getItemQuantity();
            } else {
                itemQuantity = itemQuantity - transaction.getItemQuantity();
            }
        }
        return itemQuantity;
    }

    public void displayStatistics(List<Integer> totalQuantity, List<Float> profitList) {
        List<Integer> columnWidths = List.of(5, 15, 5, 13, 16, 9);
        List<String> headers = List.of("ID", "Product", "Sold", "Cost Price", "Selling Price", "Profit");
        List<List<String>> body = parseData(inventory, totalQuantity, profitList);
        Table table = new Table(columnWidths, headers, body);
        table.showData();
    }

    private List<List<String>> parseData(List<InventoryItem> inventory, List<Integer> totalQuantity, List<Float> profitList) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            String index = String.valueOf(inventory.get(i).getId());
            String title = inventory.get(i).getTitle();
            String quantity = String.valueOf(totalQuantity.get(i));
            String costPrice = String.valueOf(inventory.get(i).getCostPrice());
            String sellingPrice = String.valueOf(inventory.get(i).getSellingPrice());
            String profit = String.valueOf(profitList.get(i));
            List<String> data = List.of(index, title, quantity, costPrice, sellingPrice, profit);
            result.add(data);
        }
        return result;
    }

    public void getBestSellingProduct(List<Integer> totalQuantity) {
        int index = totalQuantity.indexOf(Collections.max(totalQuantity));
        System.out.println("Best Selling Product is: " + inventory.get(index).getTitle().toUpperCase());
    }

    public void generateCashFlow(List<Float> outwardCashFlow, List<Float> inwardCashFlow) {
        float outCashFlow = (float) outwardCashFlow.stream().mapToDouble(Float::floatValue).sum();
        System.out.println("Total OutWard cash flow is: " + outCashFlow);
        float inCashFlow = (float) inwardCashFlow.stream().mapToDouble(Float::floatValue).sum();
        System.out.println("Total inward cash flow is: " + inCashFlow);
        System.out.println("Total profit is: " + (inCashFlow - outCashFlow));
    }
}
