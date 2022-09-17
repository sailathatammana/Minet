package cashier;

import mvc.BaseView;

import java.util.List;

public class CashierMenuView extends BaseView {
    private List<String> menuOptions;

    public CashierMenuView(List<String> menuOptions) {
        clearScreen();
        this.menuOptions = menuOptions;
    }

    public void printBanner() {
        System.out.println("Cashier menu options:");
        displayList(menuOptions);
    }
}
