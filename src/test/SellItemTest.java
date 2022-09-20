package test;

import com.minet.actions.SellItem;
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
class SellItemTest {
    static User user;
    static InventoryItem item;
    static SellItem sellItem;
    static List<InventoryItem> inventoryItemList;
    static List<Transaction> transactionList;

    @BeforeAll
    public void init() {
        user = new User("Ravi", "ravi",
                "108317418603245272581161763738331279269013253057951070798501290887753974195791", "Cashier");
        item = new InventoryItem(10, "Pencil", "To write", 10, 8,5);
        inventoryItemList = List.of(item);
        Transaction transaction = new Transaction("Pencil", 2, "Ravi", 8970267,
                20, TransactionType.SELL);
        transactionList = List.of(transaction);
        sellItem = new SellItem(user, inventoryItemList, transactionList);
    }

    @Test
    @DisplayName("Item available in the pool succesfully")
    public void getItemByNameTest() {
        assertEquals(inventoryItemList.get(0), sellItem.getItemByName("pencil"));
    }

    @Test
    @DisplayName("Check if transaction is created")
    public void addItemToTransactionTest() {
        assertFalse(sellItem.addItemToTransaction(item, 10));
    }

    @Test
    @DisplayName("Item sufficient in stock to sell")
    public void sufficientQuantityTest() {
        assertTrue(sellItem.validateQuantity(2, 5));
    }

    @Test
    @DisplayName("Item not sufficient in stock to sell")
    public void insufficientQuantityTest() {
        assertFalse(sellItem.validateQuantity(5, 2));
    }

    @Test
    @DisplayName("Requested zero quantity")
    public void invalidityQuantityTest() {
        assertFalse(sellItem.validateQuantity(0, 10));
    }

    @Test
    @DisplayName("Item out of stock to sell")
    public void outOfStockTest() {
        assertFalse(sellItem.validateQuantity(4, 1));
    }

}