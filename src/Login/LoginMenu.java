package Login;


public class LoginMenu {
    public LoginMenu(String role) {
        LoginMenuModel model = new LoginMenuModel();
        LoginMenuView view = new LoginMenuView();
        LoginMenuController controller = new LoginMenuController(model, view,role);
        controller.requestUserInput();
    }
}
