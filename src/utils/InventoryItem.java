package utils;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private final int id;
    private String title;
    private String description;
    private float sellingPrice;
    private int quantity;
    private String stockStatus;
    private float costPrice;

    public InventoryItem(int id, String title, String description, float sellingPrice, float costPrice, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.setStockStatus();
        this.setCostPrice();
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

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float price) {
        this.sellingPrice = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.setStockStatus();
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

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice() {
        this.costPrice = (float) (this.sellingPrice * 0.9);
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description + "," + sellingPrice + "," + costPrice + "," + quantity;
    }
}
