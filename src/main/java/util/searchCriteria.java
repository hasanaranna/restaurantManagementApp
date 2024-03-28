package util;

public class searchCriteria {
    private String name;
    private double score;
    private String category;
    private double minPrice;
    private double maxPrice;
    private String restaurantPrice;
    private String zipCode;
    private String price;

    public searchCriteria()
    {
        name = " ";
        score = 0;
        category = " ";
        minPrice = 0;
        maxPrice = 0;
        restaurantPrice = " ";
        zipCode = " ";
        price = " ";
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getScore() {
        return score;
    }

    public String getRestaurantPrice() {
        return restaurantPrice;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setRestaurantPrice(String restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
