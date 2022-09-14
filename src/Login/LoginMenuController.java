package Login;

import homeMenu.HomeMenu;
import mvc.BaseController;
import utils.Display;
import utils.Encrypt;
import utils.User;

public class LoginMenuController extends BaseController<User> {
    LoginMenuModel model = new LoginMenuModel();
    LoginMenuView view = new LoginMenuView();

    private final String role;

    public LoginMenuController(String role) {
        this.role = role;
    }

    public User run() {
        if (listHasUsers()) return (new User("", "", "", ""));
        String userName = view.printUserNamePrompt();
        String password = view.printPasswordPrompt();
        String encryptedPassword = Encrypt.encryptPassword(password);
        return (new User("", userName, encryptedPassword, this.role));
       /* password masking
        Console console = System.console();
        char[] input2 = console.readPassword();
        String encryptedPassword = Encrypt.encryptPassword(String.valueOf(input2));*/
    }

    private boolean listHasUsers() {
        if (model.getAllUsers().size() == 0) {
            view.printUsersEmpty();
            Display.returnMainMenu();
            new HomeMenu();
            return true;
        }
        return false;
    }


}
