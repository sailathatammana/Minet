package test;

import cashier.CashierMenuController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CashierMenuControllerTest {

    CashierMenuController controller = new CashierMenuController();

    @Test
    @DisplayName("check handle option")
    public void checkHandleOptionTest() {
        assertEquals(1, controller.handleOption(3));
        assertEquals(1, controller.handleOption(4));
        assertThrows(IndexOutOfBoundsException.class, () -> controller.handleOption(-9));
    }

}