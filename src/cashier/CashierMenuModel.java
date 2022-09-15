package cashier;

import mvc.BaseModel;

import java.util.List;

public class CashierMenuModel extends BaseModel {

    public final List<String> menuOptions = List.of("Sell an Item", "Return an Item", "Create order", "View Inventory", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }

}