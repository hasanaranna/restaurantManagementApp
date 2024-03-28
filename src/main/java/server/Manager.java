package server;
import util.*;

import java.io.Serializable;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Manager implements Serializable{
    private String foodFileName;
    private String restaurantFileName;
    private String orderFileName;
    private List<singleOrder> orderFoodList = new ArrayList<>();
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    public List<Food> getMenuListByName(String userName){
        int id = getRestaurantIdByName(userName);

        List<Food> foodList = new ArrayList<>();
        for(int i = 0; i< foods.size(); i++)
        {
            Food t = foods.get(i);
            if(t.getRestaurantId() == id)
            {
                foodList.add(t);
            }
        }

        return foodList;
    }
    public void setFoodFileName(String foodFileName) {
        this.foodFileName = foodFileName;
    }

    public void setRestaurantFileName(String restaurantFileName) {
        this.restaurantFileName = restaurantFileName;
    }

    public void setOrderFileName(String orderFileName) {
        this.orderFileName = orderFileName;
    }

    public void readFiles() throws IOException {
        BufferedReader bres = new BufferedReader(new FileReader(restaurantFileName));
        BufferedReader bmenu = new BufferedReader(new FileReader(foodFileName));
        BufferedReader border = new BufferedReader(new FileReader(orderFileName));
        //order list er kaj
        while (true) {
            String line = border.readLine();
            if (line == null) break;
            String [] array = line.split(",(?! )", -1);
            String customerName = array[0];
            String restaurantName = array[1];
            int restaurantId = Integer.parseInt(array[2]);
            String category = array[3];
            String name = array[4];
            double price = Double.parseDouble(array[5]);
            addOrder(customerName, restaurantName, restaurantId, category, name, price);
        }
        border.close();


        while (true) {
            String line = bmenu.readLine();
            if (line == null) break;
            //String [] array = line.split(",", -1);
            String [] array = line.split(",(?! )", -1);
            int restaurantId = Integer.parseInt(array[0]);
            String category = array[1];
            String name = array[2];
            double price = Double.parseDouble(array[3]);
            addFood(restaurantId, category, name, price);
        }
        bmenu.close();

        while (true) {
            String line = bres.readLine();
            if (line == null) break;
            //System.out.println(line);
            String [] array = line.split(",", -1);
            int id = Integer.parseInt(array[0]);
            String name = array[1];
            double score = Double.parseDouble(array[2]);
            String price = array[3];
            String zipCode = array[4];
            List<String> categories = new ArrayList<>();
            for (int i = 5; i < array.length; i++) {
                if (!array[i].isEmpty()) {
                    categories.add(array[i]);
                }
            }
            addRestaurant(id, name, score, price, zipCode, categories);

        }
        System.out.println("File reading done!");
        bmenu.close();
    }
    public void reloadOrderList()
    {
        orderFoodList.clear();
    }
    private void addOrder(String customerName, String restaurantName, int restaurantId,
                          String category, String name, double price)
    {
        orderFoodList.add(new singleOrder(customerName, restaurantName, restaurantId, category,
                name, price));

    }

    public List<singleOrder> getOrderFoodList() {
        return orderFoodList;
    }

    public int restaurantCount() {
        return restaurants.size();
    }
    public void addFood(int restaurantId,String category,String name,double price) {
        Food f = new Food(restaurantId, category, name, price);
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == restaurantId && t.getName().equalsIgnoreCase(name) && t.getPrice() == price && t.getCategory().equalsIgnoreCase(category)) {
                //System.out.println("Food item already present in this restaurant!");
                return;
            }
        }
        foods.add(f);
        //newFood.add(f);
    }
    public void addNewFood(int restaurantId,String category,String name,double price) {
        Food f = new Food(restaurantId, category, name, price);
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == restaurantId && t.getName().equalsIgnoreCase(name) && t.getPrice() == price && t.getCategory().equalsIgnoreCase(category)) {
                //System.out.println("Food item already present in this restaurant!");
                return;
            }
        }
        foods.add(f);
        printingInMenu();
        //displayAllFoodDetails();
        //newFood.add(f);
    }
    public void addRestaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
        List<Food> menuList = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == id ) {
                menuList.add(t);
            }
        }
        Restaurant r = new Restaurant(id, name, score, price, zipCode, categories,menuList);
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (t.getName().equalsIgnoreCase(name)) {
                //System.out.println("Restaurant already present in the list!");
                return;
            }
        }
        restaurants.add(r);
        //newRestaurant.add(r);

    }
    public void addNewRestaurant(String name, double score, String price, String zipCode, List<String> categories) {
        int id = restaurantCount() + 1;
        List<Food> menuList = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == id ) {
                menuList.add(t);
            }
        }
        Restaurant r = new Restaurant(id, name, score, price, zipCode, categories,menuList);
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (t.getName().equalsIgnoreCase(name)) {
                //System.out.println("Restaurant already present in the list!");
                return;
            }
        }
        restaurants.add(r);
        System.out.println("New Restaurant Added");
        printingInRestaurant();
        //displayAllRestaurantDetails();
        //newRestaurant.add(r);

    }

    public boolean subStringBelongs(String sub, String main) {
        sub = sub.toLowerCase();
        main = main.toLowerCase();

        if (main.contains(sub)) {
            int i = main.indexOf(sub);

            if (i == 0 || main.charAt(i - 1) == ' ') {
                return true;
            }
        }
        return false;
    }
    public List<Restaurant> searchRestaurantsByName(String name){
        List<Restaurant>foundRestaurantByName = new ArrayList<>();
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (subStringBelongs(name, t.getName())) {
                foundRestaurantByName.add(t);
            }
        }
        return foundRestaurantByName;
    }
    public void displayAllRestaurantDetails()
    {
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            displayRestaurantDetails(t);
        }
    }
    public void displayAllFoodDetails()
    {
        for (int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            displayFoodDetails(t);
        }
    }
    public void displayRestaurantDetails(Restaurant restaurant){
        System.out.println("ID: " + restaurant.getId());
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Price: " + restaurant.getPrice());
        System.out.println("Zip Code: " + restaurant.getZipCode());
        System.out.println("Categories: " + restaurant.getCategories());
    }

    public List<Restaurant> searchRestaurantsByScore(double minScore, double maxScore){
        List<Restaurant>foundRestaurantByScore = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (t.getScore()>= minScore && t.getScore()<=maxScore) {
                foundRestaurantByScore.add(t);
            }
        }
        return foundRestaurantByScore;
    }

    public List<Restaurant> searchRestaurantsByCategory(String category){
        List<Restaurant>foundRestaurantByCategory = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            List<String>categoryList = new ArrayList<>();
            categoryList = t.getCategories();
            for(String cat:categoryList){
                if(subStringBelongs(category, cat)){
                    foundRestaurantByCategory.add(t);
                }
            }
        }
        return foundRestaurantByCategory;
    }

    public List<Restaurant> searchRestaurantsByPrice(String price){
        List<Restaurant>foundRestaurantByPrice = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (price.equalsIgnoreCase(t.getPrice())) {
                foundRestaurantByPrice.add(t);
            }
        }
        return foundRestaurantByPrice;
    }

    public List<Restaurant> searchRestaurantsByZipCode(String zipCode){
        List<Restaurant>foundRestaurantByZip = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (zipCode.equalsIgnoreCase(t.getZipCode())) {
                foundRestaurantByZip.add(t);
            }
        }
        return foundRestaurantByZip;
    }

    public Set<String> foodCategories(){
        Set<String> foodCategories = new HashSet<>();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            List<String>categoryList = new ArrayList<>();
            categoryList = t.getCategories();
            for(String cat:categoryList){
                if(i==0){
                    foodCategories.add(cat);
                }
                else if(!foodCategories.contains(cat)){
                    foodCategories.add(cat);
                }
            }
        }
        return foodCategories;
    }

    public List<Food> searchFoodByName(String name){
        List<Food>foundFoodByName = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (subStringBelongs(name, t.getName())) {
                foundFoodByName.add(t);
            }
        }
        return foundFoodByName;
    }
    public void displayFoodDetails(Food food){
        System.out.println("Name: "+food.getName());
        System.out.println("Category: "+food.getCategory());
        System.out.println("Price:"+food.getPrice());
        System.out.println("Restaurant: "+ getRestaurantNameById(food.getRestaurantId()));
    }
    public String getRestaurantNameById(int id){
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }

    public Restaurant getRestaurantByName(String restaurantName){
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (restaurantName.equalsIgnoreCase(t.getName())) {
                return t;
            }
        }
        return null;
    }
    public int getRestaurantIdByName(String name){
        for(int i = 0; i < restaurants.size(); i++) {
            Restaurant t = restaurants.get(i);
            if (t.getName().equalsIgnoreCase(name)) {
                return t.getId();
            }
        }
        return -1;
    }
    public List<Food> searchFoodByNameInRestaurant(String foodName, String restaurantName){
        int resId = getRestaurantIdByName(restaurantName);
        List<Food>foundFoodByName = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == resId) {
                if(t.getName().equalsIgnoreCase(foodName)){
                    foundFoodByName.add(t);
                }
            }
        }
        return foundFoodByName;
    }

    public List<String> categoryWiseList(String name){
        List<String>restaurantName = new ArrayList<>();
        for(Restaurant r:restaurants){
            List<String>categoryList = r.getCategories();
            for(String cat:categoryList){
                if(name.equalsIgnoreCase(cat)){
                    if(restaurantName.size()>0){
                        String newName = ", "+r.getName();
                        restaurantName.add(newName);
                    }
                    else{
                        restaurantName.add(r.getName());
                    }
                }
            }
        }
        return restaurantName;
    }
    public List<Food> searchFoodByCategory(String category){
        List<Food>foundFoodByCategory = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (subStringBelongs(category, t.getCategory())) {
                foundFoodByCategory.add(t);
            }
        }
        return foundFoodByCategory;
    }
    public List<Food> searchFoodByCategoryInRestaurant(String foodCategory,String restaurantName){
        int resId = getRestaurantIdByName(restaurantName);
        List<Food>foundFoodByCategory = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == resId) {
                if(t.getCategory().equalsIgnoreCase(foodCategory)){
                    foundFoodByCategory.add(t);
                }
            }
        }
        return foundFoodByCategory;
    }

    public List<Food> searchFoodByPriceRange(double minPrice,double maxPrice){
        List<Food>foundFoodByPrice = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getPrice()>= minPrice && t.getPrice()<=maxPrice) {
                foundFoodByPrice.add(t);
            }
        }
        return foundFoodByPrice;
    }
    public List<Food> searchFoodByPriceRangeInRestaurant(double minPrice,double maxPrice,String restaurantName){
        int resId = getRestaurantIdByName(restaurantName);
        List<Food>foundFoodByPrice = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == resId) {
                if(t.getPrice()>= minPrice && t.getPrice()<=maxPrice){
                    foundFoodByPrice.add(t);
                }
            }
        }
        return foundFoodByPrice;
    }

    public List<Food> costliestFoodItems(String restaurantName){
        int resId = getRestaurantIdByName(restaurantName);
        double max = 0.0;
        List<Food>foundFoodByPrice = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == resId) {
                if(t.getPrice()>= max){
                    max = t.getPrice();
                }
            }
        }
        for(int i = 0; i < foods.size(); i++) {
            Food t = foods.get(i);
            if (t.getRestaurantId() == resId) {
                if(t.getPrice() == max){
                    foundFoodByPrice.add(t);
                }
            }
        }
        return foundFoodByPrice;
    }

    public int foodItemCount(Restaurant r)
    {
        int count = 0;
        for(Food f:foods){
            if(f.getRestaurantId() == r.getId()){
                count++;
            }
        }
        return count;
    }
    public List<String> restaurantFoodCount(){
        List<String> restaurantFoodCount = new ArrayList<>();
        for(Restaurant restaurant: restaurants){
            int count = foodItemCount(restaurant);
            String str = restaurant.getName()+": "+count;
            restaurantFoodCount.add(str);
        }
        return restaurantFoodCount;
    }
    public void printingInRestaurant() {
        try {
            FileWriter fileWriter = new FileWriter(restaurantFileName);
            for(Restaurant r: restaurants){
                String str1, str2,str3;
                int size = r.getCategories().size();
                str1 = r.getCategories().get(0);
                if(size == 2 || size == 3){
                    str2 = r.getCategories().get(1);
                }
                else{
                    str2 = "";
                }
                if(size == 3) {
                    str3 = r.getCategories().get(2);
                }
                else{
                    str3 = "";
                }
                String str = r.getId()+","+r.getName()+","+r.getScore()+","+r.getPrice()
                        +","+r.getZipCode()+","+str1+","+str2+","+str3+"\n";

                fileWriter.write(str);
            }
            fileWriter.close();

            System.out.println("Content appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printingInMenu() {
        try {
            FileWriter fileWriter = new FileWriter(foodFileName);

            for(Food food: foods){
                String str = food.getRestaurantId()+","+food.getCategory()+","+food.getName()+","+food.getPrice()+"\n";
                fileWriter.write(str);
            }
            fileWriter.close();
            System.out.println("Content appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAll() {
    }
}