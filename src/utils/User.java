package utils;

import java.io.Serializable;

public class User implements Serializable {
    private final String fullName;
    private String userName;
    private String password;
    private String userRole;

    public User(String fullName, String userName, String password, String userRole) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String balance) {
        this.userRole = balance;
    }

    @Override
    public String toString() {
        return fullName + "," + userName + "," + password + "," + userRole;
    }
}