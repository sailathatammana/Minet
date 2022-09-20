package com.minet.login;

import com.minet.homeMenu.HomeMenu;
import com.minet.mvc.BaseController;
import com.minet.utils.Display;
import com.minet.utils.Encrypt;
import com.minet.utils.User;

public class LoginMenuController extends BaseController<User> {
    LoginMenuModel model = new LoginMenuModel();
    LoginMenuView view = new LoginMenuView();

    private final String role;

    public LoginMenuController(String role) {
        this.role = role;
        model.getAllUsers();
    }

    public User run() {
        if (listHasUsers()) return (new User("", "", "", ""));
        String userName = view.printUserNamePrompt();
        String password = view.printPasswordPrompt();
        String encryptedPassword = Encrypt.encryptPassword(password);
        return (new User("", userName, encryptedPassword, this.role));
    }

    private boolean listHasUsers() {
        if (model.users.size() == 0) {
            view.printUsersEmpty();
            Display.returnMainMenu();
            new HomeMenu();
            return true;
        }
        return false;
    }


}
