package test;

import com.minet.actions.ReviewOrders;
import com.minet.utils.InventoryItem;
import com.minet.utils.OrderList;
import com.minet.utils.OrderStatusType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReviewOrdersTest {
    static InventoryItem item;
    static OrderList orderList;
    static ReviewOrders reviewOrder;
    static List<InventoryItem> inventoryItemList;
    static List<OrderList> orderLists;
    static List<OrderList> orderedTransactionList;

    @BeforeAll
    public void init() {
        item = new InventoryItem(10, "Pencil", "To write", 10, 8, 5);
        inventoryItemList = List.of(item);
        orderList = new OrderList(1234, item, "Ravi", OrderStatusType.PENDING);
        orderLists = List.of(orderList);
        reviewOrder = new ReviewOrders(inventoryItemList, orderLists, orderedTransactionList);
    }

    @Test
    @DisplayName("Getting the order with the orderID from orderlists")
    void getItemByIdTest() {
        assertEquals(orderList, reviewOrder.getItemById(1234));
    }

    @Test
    @DisplayName("Raise an exception when an order is not available in orderlist")
    void raiseExceptionTest() {
        assertThrows(NoSuchElementException.class, () -> reviewOrder.getItemById(12345));
    }

    @Test
    @DisplayName("Invalid input while confirming the order tested successfully")
    void invalidAddTransactionTest() {
        assertFalse(reviewOrder.addTransaction(orderList, "Nope"));
    }
}