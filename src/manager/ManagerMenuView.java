package manager;

import mvc.BaseView;

import java.util.List;

public class ManagerMenuView extends BaseView {
    private final List<String> menuOptions;

    public ManagerMenuView(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void printBanner() {
        clearScreen();
        System.out.println("Cashier menu options:");
        displayList(menuOptions);
    }
}