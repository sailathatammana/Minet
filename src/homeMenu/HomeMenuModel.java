package homeMenu;

import mvc.BaseModel;

import java.util.List;

public class HomeMenuModel extends BaseModel {
    public final List<String> menuOptions = List.of("Admin", "Cashier", "Manager", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }

}
