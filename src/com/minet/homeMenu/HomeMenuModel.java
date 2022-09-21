package com.minet.homeMenu;

import com.minet.mvc.BaseModel;

import java.util.List;

public class HomeMenuModel extends BaseModel {
    private final List<String> menuOptions = List.of("Admin", "Cashier", "Manager", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }

}
