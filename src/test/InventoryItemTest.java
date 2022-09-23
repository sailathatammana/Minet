package test;

import com.minet.utils.InventoryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryItemTest {
    static InventoryItem item;

    @BeforeEach
    public void init() {
        item = new InventoryItem(5, "pencil", "to write", 5, 8, 1);
    }

    @Test
    @DisplayName("Check if item out of stock")
    public void checkOutOfStockStatusTest() {
        assertEquals("Out of stock", item.getStockStatus());
    }

    @Test
    @DisplayName("Check Item in stock")
    public void checkInStockStatusTest() {
        item.setQuantity(5);
        assertEquals("In stock", item.getStockStatus());
    }


}