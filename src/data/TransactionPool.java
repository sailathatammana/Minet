package data;

import utils.FileHandler;
import utils.Transaction;
import utils.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private final static List<Transaction> transactionList = new ArrayList<>();
    static FileHandler<Transaction> fileHandler = new FileHandler<>();

    public static List<Transaction> getAllTransactions() {
        List<List<String>> result = fileHandler.readFromFile("assets/transactions.txt");
        for (List<String> strings : result) {
            String inventoryItem = strings.get(0);
            int itemQuantity = Integer.parseInt(strings.get(1));
            String cashierName = strings.get(2);
            int receiptNumber = Integer.parseInt(strings.get(3));
            float amount = Float.parseFloat(strings.get(4));
            TransactionType type = TransactionType.valueOf(strings.get(5));
            transactionList.add(new Transaction(inventoryItem, itemQuantity, cashierName, receiptNumber, amount, type));
        }
        return transactionList;
    }
}
