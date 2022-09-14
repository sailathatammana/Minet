package Login;

import utils.FileHandler;
import utils.User;

import java.util.ArrayList;
import java.util.List;

public class LoginMenuModel {
    protected List<User> users = new ArrayList<>();

    FileHandler<User> fileHandler = new FileHandler<>();

    public List<User> getAllUsers() {
        List<List<String>> result = fileHandler.readFromFile("assets/users.txt");
        for (int i = 0; i < result.size(); i++) {
            users.add(new User(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2), result.get(i).get(3)));
        }
        return users;
    }

}
