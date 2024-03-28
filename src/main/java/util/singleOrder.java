package util;

import java.io.Serializable;

public class singleOrder implements Serializable {
    private String customerName;
    private String restaurantName;
    private int restaurantId;
    private String category;
    private String name;
    private double price;

    public singleOrder(String customerName, String restaurantName, int id, String name,
                       String category, double price)
    {
        this.category = category;
        this.customerName =customerName;
        this.restaurantId = id;
        this.price = price;
        this.restaurantName = restaurantName;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
