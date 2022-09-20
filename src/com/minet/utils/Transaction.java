package com.minet.utils;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String itemName;
    private int itemQuantity;
    private String cashierName;
    private int receiptNumber;
    private float amount;
    private TransactionType transactionType;

    public Transaction(String itemName, int itemQuantity, String cashierName, int receiptNumber, float amount, TransactionType transactionType) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.cashierName = cashierName;
        this.receiptNumber = receiptNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return itemName + "," + itemQuantity + "," + cashierName + "," + receiptNumber + "," + amount + "," + transactionType;
    }
}
