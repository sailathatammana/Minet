package test;

import com.minet.actions.CreateOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.minet.utils.InventoryItem;
import com.minet.utils.OrderList;
import com.minet.utils.OrderStatusType;
import com.minet.utils.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateOrderTest {
    static User user;
    static InventoryItem item;
    static OrderList orderList;
    static CreateOrder createOrder;
    static List<InventoryItem> inventoryItemList;
    static List<OrderList> orderLists;

    @BeforeAll
    public void init() {
        user = new User("Ravi", "ravi",
                "108317418603245272581161763738331279269013253057951070798501290887753974195791", "Cashier");
        item = new InventoryItem(10, "Pencil", "To write", 10, 8,5);
        inventoryItemList = List.of(item);
        orderList = new OrderList(1234, item, "Ravi", OrderStatusType.PENDING);
        orderLists = List.of(orderList);
        createOrder = new CreateOrder(user, inventoryItemList, orderLists);
    }

    @Test
    @DisplayName("Requested zero quantity")
    public void invalidityQuantityTest() {
        assertFalse(createOrder.validatePositiveQuantity(0));
    }


    @Test
    @DisplayName("Requested negative quantity")
    public void negativeInvalidityQuantityTest() {
        assertFalse(createOrder.validatePositiveQuantity(-5));
    }

    @Test
    @DisplayName("Requested positive quantity")
    public void positiveQuantityTest() {
        assertTrue(createOrder.validatePositiveQuantity(2));
    }

    @Test
    @DisplayName("Item available in the pool succesfully")
    public void getItemByNameTest() {
        assertEquals(inventoryItemList.get(0), createOrder.getItemByName("pencil"));
    }

    @Test
    @DisplayName("Update  existing order quantity in orderlist successfully")
    public void addItemToOrderTest() {
        assertTrue(createOrder.addItemToOrder(item, "Pencil", 10));
    }

    @Test
    @DisplayName("Negative input order quantity is tested successfully")
    public void negativeQuantityCheckTest() {
        assertFalse(createOrder.addItemToOrder(item, "Pencil", -10));
    }

    @Test
    @DisplayName("Requested item not available in pool")
    public void updateOrderInPoolTest() {
        assertFalse(createOrder.updateOrderInPool("Pen", 10));
    }
}