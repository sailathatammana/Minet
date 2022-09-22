package com.minet.login;

import com.minet.utils.FileHandler;
import com.minet.utils.User;

import java.util.ArrayList;
import java.util.List;

public class LoginMenuModel {
    protected List<User> users = new ArrayList<>();

    FileHandler<User> fileHandler = new FileHandler<>();

    public LoginMenuModel() {
        getAllUsers();
    }

    public void getAllUsers() {
        List<List<String>> result = fileHandler.readFromFile("assets/users.txt");
        result.forEach(item -> users.add(new User(item.get(0), item.get(1), item.get(2), item.get(3))));
    }

}
