package com.minet.manager;

import com.minet.mvc.BaseModel;

import java.util.List;

public class ManagerMenuModel extends BaseModel {
    private final List<String> menuOptions = List.of("View Inventory", "Review orderList",
            "View OrderedTransaction List", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }
}
