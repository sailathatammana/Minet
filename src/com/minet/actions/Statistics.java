package com.minet.actions;

import com.minet.utils.Display;
import com.minet.utils.InventoryItem;
import com.minet.utils.Transaction;
import com.minet.utils.TransactionType;

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
        displayProfits(totalQuantity, profit);
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

    public void displayProfits(List<Integer> totalQuantity, List<Float> profitList) {
        String tableHeader = "| Id    | Product         | Sold  | Cost Price  | Selling Price  | Profit  |%n";
        String tableBorder = "+-------+-----------------+-------+-------------+----------------+---------+%n";
        String tableFormat = "| %-5d | %-15s | %-5d | %-11.2f | %-14.2f | %-7.2f |%n";
        System.out.format(tableBorder);
        System.out.format(tableHeader);
        System.out.format(tableBorder);
        for (int i = 0; i < inventory.size(); i++) {
            int id = inventory.get(i).getId();
            String title = inventory.get(i).getTitle();
            float profit = profitList.get(i);
            int quantity = totalQuantity.get(i);
            float sellingPrice = inventory.get(i).getSellingPrice();
            float costPrice = inventory.get(i).getCostPrice();
            System.out.format(tableFormat, id, title, quantity, costPrice, sellingPrice, profit);
        }
        System.out.format(tableBorder);
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
