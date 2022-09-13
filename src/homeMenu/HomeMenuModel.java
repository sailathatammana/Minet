package homeMenu;

import Login.LoginMenu;
import utils.Display;

import java.util.List;

public class HomeMenuModel {
    public final List<String> menuOptions = List.of("Admin", "Cashier", "Manager", "Exit");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1, 2, 3 -> new LoginMenu(menuOptions.get(selectedOption - 1));
            case 4 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
