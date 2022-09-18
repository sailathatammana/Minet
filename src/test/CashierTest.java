package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import userRoles.Cashier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CashierParameterResolver.class)
class CashierTest {
    @Test
    @DisplayName("Item available in the pool succesfully")
    public void sellItemTest(Cashier cashier) {
        assertEquals(cashier.getInventoryList().get(0), cashier.getItemByName("pen"));
    }

    @Test
    @DisplayName("Receipt Number validated succesfully")
    public void validateReceiptNumber(Cashier cashier) {
        assertEquals(cashier.getTransactionList().get(12), cashier.getItemByReceiptNumber(5017364));
    }

    @Test
    @DisplayName("Item sufficient in stock to sell")
    public void sufficientQuantityTest(Cashier cashier) {
        assertTrue(cashier.validateQuantity(2, 5));
    }

    @Test
    @DisplayName("Item not sufficient in stock to sell")
    public void insufficientQuantityTest(Cashier cashier) {
        assertFalse(cashier.validateQuantity(5, 2));
    }

    @Test
    @DisplayName("Requested zero quantity")
    public void invalidityQuantityTest(Cashier cashier) {
        assertFalse(cashier.validateQuantity(0, 10));
        assertFalse(cashier.validatePositiveQuantity(0));
    }

    @Test
    @DisplayName("Item out of stock to sell")
    public void outOfStockTest(Cashier cashier) {
        assertFalse(cashier.validateQuantity(4, 1));
    }

    @Test
    @DisplayName("Requested negative quantity")
    public void negativeInvalidityQuantityTest(Cashier cashier) {
        assertFalse(cashier.validatePositiveQuantity(-5));
    }

}