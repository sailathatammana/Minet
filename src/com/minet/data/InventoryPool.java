package com.minet.data;

import com.minet.utils.FileHandler;
import com.minet.utils.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryPool {
    private final static List<InventoryItem> inventory = new ArrayList<>();
    static FileHandler<InventoryItem> fileHandler = new FileHandler<>();

    public static List<InventoryItem> getfullInventory() {
        List<List<String>> result = fileHandler.readFromFile("assets/inventory.txt");
        result.forEach(item -> {
            int id = Integer.parseInt(item.get(0));
            String title = item.get(1);
            String description = item.get(2);
            float sellingPrice = Float.parseFloat(item.get(3));
            float costPrice = Float.parseFloat(item.get(4));
            int quantity = Integer.parseInt(item.get(5));
            inventory.add(new InventoryItem(id, title, description, sellingPrice, costPrice, quantity));
        });
        return inventory;
    }
}
