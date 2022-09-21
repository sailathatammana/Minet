package com.minet.admin;

import com.minet.mvc.BaseModel;

import java.util.List;

public class AdminMenuModel extends BaseModel {
    private final List<String> menuOptions = List.of("Sell an Item", "Return an Item", "Create order",
            "View Inventory", "Review orderList", "View OrderedTransaction List", "Generate Statistics", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }
}
