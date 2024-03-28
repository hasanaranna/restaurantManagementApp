package util;

import java.util.List;

public class Order implements java.io.Serializable {
    //private int customerId;
    private List<Food> food;
    private String customerName;
    private String restaurantName;
    //Boolean isAccepted;

    public Order(String customerName,String restaurantName, List<Food> food) {
        //this.customerId = customerId;
        this.customerName = customerName;
        this.food = food;
        this.restaurantName = restaurantName;
        //this.isAccepted = isAccepted;
    }

    /*public Order() {
        this.customerId = 0;
        this.food = new Food();
        isAccepted = false;
    }*/

    /*public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }*/
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    /*public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public int getCustomerId() {
        return customerId;
    }*/

    public List<Food> getFood() {
        return food;
    }

    public String getCustomerName() {
        return customerName;
    }

    /*public int getResId() {
        return food.getRestaurantId();
    }*/

    /*public boolean isAccepted() {
        return false;
    }*/
}
