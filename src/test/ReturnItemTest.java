package test;

import com.minet.actions.ReturnItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.minet.utils.InventoryItem;
import com.minet.utils.Transaction;
import com.minet.utils.TransactionType;
import com.minet.utils.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReturnItemTest {
    static User user;
    static InventoryItem item;
    static Transaction transaction1,transaction2,transaction3;
    static ReturnItem returnItem;
    static List<InventoryItem> inventoryItemList;
     List<Transaction> transactionList;

    @BeforeAll
    public void init() {
        user = new User("Ravi", "ravi",
                "108317418603245272581161763738331279269013253057951070798501290887753974195791", "Cashier");
        item = new InventoryItem(10, "Pencil", "To write", 10, 8,5);
        inventoryItemList = List.of(item);
         transaction1 = new Transaction("Pencil", 2, "Ravi", 8970267,
                20, TransactionType.SELL);
         transaction2=(new Transaction("Eraser", 2, "Ravi", 1234567,
                20, TransactionType.SELL));
         transaction3=(new Transaction("Eraser", 2, "Ravi", 1234567,
                20, TransactionType.RETURN));
        transactionList = List.of(transaction1,transaction2,transaction3);
        returnItem = new ReturnItem(user, inventoryItemList, transactionList);
    }
    @Test
    @DisplayName("Receipt Number validated succesfully")
    public void validateReceiptNumberTest() {
        assertEquals(transactionList.get(0), returnItem.getItemByReceiptNumber(8970267));
    }

    @Test
    @DisplayName("Receipt Number does not exist")
    public void validateNoReceiptNumberTest() {
        assertNull(returnItem.getItemByReceiptNumber(8978689));
    }

    @Test
    @DisplayName("Receipt Number already returned")
    public void validateReturnReceiptNumberTest() {
        assertNull(returnItem.getItemByReceiptNumber(1234567));
    }
    @Test
    @DisplayName("Check if transaction is not created")
    public void addItemToTransactionTest() {
        assertFalse(returnItem.addReturnItem(null,1234567));
    }

}