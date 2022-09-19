package admin;

import mvc.BaseView;

import java.util.List;

public class AdminMenuView extends BaseView {
    private final List<String> menuOptions;

    public AdminMenuView(List<String> menuOptions) {
        clearScreen();
        this.menuOptions = menuOptions;
    }

    public void printBanner() {
        System.out.println("Cashier menu options:");
        displayList(menuOptions);
    }
}
