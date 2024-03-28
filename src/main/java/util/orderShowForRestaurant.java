package util;

public class orderShowForRestaurant {
    private String foodName;
    private String customerName;
    private double price;
    private String category;

    public String getCategory() {
        return category;
    }
    public orderShowForRestaurant(String foodName, String customerName,
                           double price, String category)
    {
        this.category=category;
        this.price = price;
        this.customerName = customerName;
        this.foodName = foodName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public double getPrice() {
        return price;
    }

    public String getFoodName() {
        return foodName;
    }
}
