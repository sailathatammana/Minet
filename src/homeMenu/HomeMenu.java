package homeMenu;

import login.LoginMenu;

public class HomeMenu {
    HomeMenuController controller = new HomeMenuController();

    public void start() {
        while (true) {
            String role = controller.run();
            if (controller.model.menuOptions.contains(role)) {
                LoginMenu loginMenu = new LoginMenu(role);
                loginMenu.start();
                break;
            }
        }
    }
}
