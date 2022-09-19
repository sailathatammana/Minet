package manager;

import mvc.BaseModel;

import java.util.List;

public class ManagerMenuModel extends BaseModel {
    public final List<String> menuOptions = List.of("View Inventory", "Review orderList",
            "View OrderedTransaction List", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }
}
