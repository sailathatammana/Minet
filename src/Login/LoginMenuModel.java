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
        for (List<String> strings : result) {
            users.add(new User(strings.get(0), strings.get(1), strings.get(2), strings.get(3)));
        }
        return users;
    }

}
