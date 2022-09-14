package cashier;

import mvc.BaseView;

import java.util.List;

public class CashierMenuView extends BaseView {
    public CashierMenuView(List<String> menuOptions) {
        clearScreen();
        System.out.println("Cashier menu options:");
        displayList(menuOptions);
    }
}
