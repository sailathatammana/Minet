package utils;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private final int id;
    private String title;
    private String description;
    private float price;
    private int quantity;
    private String stockStatus;
    private float cost;

    public InventoryItem(int id, String title, String description, float price, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.setStockStatus();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus() {
        if (this.getQuantity() <= 1) {
            this.stockStatus = "Out of stock";
        } else {
            this.stockStatus = "In stock";
        }
    }

    public float getCost() {
        return cost;
    }

    public void setCost() {
        this.cost = this.getQuantity()*this.getPrice();
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description + "," + price + "," + quantity;
    }
}
