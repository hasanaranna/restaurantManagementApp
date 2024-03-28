package util;
import java.util.List;
import java.io.Serializable;
public class Restaurant implements Serializable {
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private List<String> categories;
    private List<Food> menuList;

    public Restaurant(int id, String name, double score, String price, String zipCode,
     List<String> categories, List<Food>menuList) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.categories = categories;
        this.menuList = menuList;
    }


    public List<Food> getMenuList(){
        return menuList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getPrice() {
        return price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setMenuList(List<Food>menuList){
        this.menuList = menuList;
    }

}
