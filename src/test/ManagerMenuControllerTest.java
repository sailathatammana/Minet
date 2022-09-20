package test;

import com.minet.manager.ManagerMenuController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManagerMenuControllerTest {

    ManagerMenuController menuController = new ManagerMenuController();

    @Test
    @DisplayName("check handle option")
    public void checkHandleOptionTest() {
        assertEquals(1, menuController.handleOption(1));
        assertEquals(1, menuController.handleOption(2));
        assertThrows(IndexOutOfBoundsException.class, () -> menuController.handleOption(9));
    }
}