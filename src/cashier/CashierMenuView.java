package cashier;

import mvc.BaseView;

import java.util.List;

public class CashierMenuView extends BaseView {
    private List<String> menuOptions;

    public CashierMenuView(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void printBanner() {
        clearScreen();
        System.out.println("Cashier menu options:");
        displayList(menuOptions);
    }
}
