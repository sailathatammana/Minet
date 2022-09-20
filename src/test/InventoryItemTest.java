package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.minet.utils.InventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryItemTest {

    InventoryItem item = new InventoryItem(5, "pencil", "to write", 5, 8,1);

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