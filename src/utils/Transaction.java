package utils;

public class Transaction {
    private String itemName;
    private String cashierName;
    private int receiptNumber;
    private float amount;
    private TransactionType transactionType;

    public Transaction(String itemName, String cashierName, int receiptNumber, float amount, TransactionType transactionType) {
        this.itemName = itemName;
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

    public String getInventoryItem() {
        return itemName;
    }

    public void setInventoryItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return itemName + "," + cashierName + "," + receiptNumber + "," + amount + "," + transactionType;
    }
}
