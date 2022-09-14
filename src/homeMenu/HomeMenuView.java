package homeMenu;

import mvc.BaseView;

import java.util.List;

public class HomeMenuView extends BaseView {
    public HomeMenuView(List<String> menuOptions) {
        clearScreen();
        System.out.println("Login menu options:");
        displayList(menuOptions);
    }
}
