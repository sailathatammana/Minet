package com.minet.admin;

import com.minet.mvc.BaseModel;

import java.util.List;

public class AdminMenuModel extends BaseModel {
    public final List<String> menuOptions = List.of("Sell an Item", "Return an Item", "Create order",
            "View Inventory", "Review orderList", "View OrderedTransaction List", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }
}
