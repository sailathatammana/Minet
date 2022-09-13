package homeMenu;

public class HomeMenu {
    public HomeMenu() {
        HomeMenuModel model = new HomeMenuModel();
        new HomeMenuView(model.getMenuOptions());
        HomeMenuController controller = new HomeMenuController(model);
        controller.requestUserInput();
    }
}
