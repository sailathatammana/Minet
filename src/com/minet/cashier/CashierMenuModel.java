package com.minet.cashier;

import com.minet.mvc.BaseModel;

import java.util.List;

public class CashierMenuModel extends BaseModel {

    private final List<String> menuOptions = List.of("Sell an Item", "Return an Item", "Create order",
            "View Inventory", "View orderList", "Exit");

    @Override
    public List<String> getMenuOptions() {
        return menuOptions;
    }

}
