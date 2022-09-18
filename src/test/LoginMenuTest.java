package test;

import login.LoginMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginMenuTest {

    LoginMenu loginMenu = new LoginMenu("Cashier");

    @Test
    @DisplayName("Check user credentials are valid")
    public void validateUserCredentialsTest() {
        assertTrue(loginMenu.validateLoginDetails("ravi", "108317418603245272581161763738331279269013253057951070798501290887753974195791"));
        assertFalse(loginMenu.validateLoginDetails("ravi", "1234567890"));
    }

}