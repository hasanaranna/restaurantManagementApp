package server;
import util.Food;
import util.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MainApp{
    public static void main(String[] args) throws Exception{
        Manager manager = new Manager();
        BufferedReader bres = new BufferedReader(new FileReader("restaurant.txt"));
        BufferedReader bmenu = new BufferedReader(new FileReader("menu.txt"));
        while (true) {
            String line = bmenu.readLine();
            if (line == null) break;
            //String [] array = line.split(",", -1);
            String [] array = line.split(",(?! )", -1);
            int restaurantId = Integer.parseInt(array[0]);
            String category = array[1];
            String name = array[2];
            double price = Double.parseDouble(array[3]);
            manager.addFood(restaurantId, category, name, price);
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
            //manager.addRestaurant(id, name, score, price, zipCode, categories);

        }
        bmenu.close();

        //user interface
        Scanner scanner = new Scanner(System.in);

        int option;
        int choice;
        int choiceFoodMenu;

        do {
            System.out.println("Main Menu:");
            System.out.println("1)Search Restaurants");
            System.out.println("2)Search Food Items");
            System.out.println("3)Add Restaurant");
            System.out.println("4)Add Food Item to the Menu");
            System.out.println("5)Exit System");
            System.out.print("Option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:

                    do {
                        System.out.println("Restaurant Searching Options: ");
                        System.out.println("1)By Name");
                        System.out.println("2)By Score");
                        System.out.println("3)By Category");
                        System.out.println("4)By Price");
                        System.out.println("5)By Zip Code");
                        System.out.println("6)Different Category Wise List of Restaurants");
                        System.out.println("7)Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {

                            case 1:
                                System.out.print("Enter restaurant name: ");
                                String name = scanner.nextLine();
                                List<Restaurant> foundRestaurantByName = manager.searchRestaurantsByName(name);
                                if (!foundRestaurantByName.isEmpty()) {
                                    for (Restaurant restaurant : foundRestaurantByName) {
                                        manager.displayRestaurantDetails(restaurant);
                                    }
                                } else {
                                    System.out.println("No such restaurant with this name");
                                }
                                break;

                            case 2:
                                System.out.print("Enter minimum score: ");
                                double minScore = scanner.nextDouble();
                                System.out.print("Enter maximum score: ");
                                double maxScore = scanner.nextDouble();
                                List<Restaurant> foundRestaurantsByScore = manager.searchRestaurantsByScore(minScore, maxScore);
                                if (!foundRestaurantsByScore.isEmpty()) {
                                    for (Restaurant restaurant : foundRestaurantsByScore) {
                                        manager.displayRestaurantDetails(restaurant);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such restaurant with this score range");
                                }
                                break;
                            case 3:
                                System.out.print("Enter category: ");
                                String category = scanner.nextLine();
                                List<Restaurant> foundRestaurantByCategory = manager.searchRestaurantsByCategory(category);
                                if (!foundRestaurantByCategory.isEmpty()) {
                                    for (Restaurant restaurant : foundRestaurantByCategory) {
                                        manager.displayRestaurantDetails(restaurant);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such restaurant with this category");
                                }
                                break;
                            case 4:
                                System.out.print("Enter price($,$$ or $$$): ");
                                String price = scanner.nextLine();
                                List<Restaurant> foundRestaurantByPrice = manager.searchRestaurantsByPrice(price);
                                if (!foundRestaurantByPrice.isEmpty()) {
                                    for (Restaurant restaurant : foundRestaurantByPrice) {
                                        manager.displayRestaurantDetails(restaurant);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such restaurant with this price");
                                }
                                break;
                            case 5:
                                System.out.print("Enter Zip Code: ");
                                String zipCode = scanner.nextLine();
                                List<Restaurant> foundRestaurantByZipCode = manager.searchRestaurantsByZipCode(zipCode);
                                if (!foundRestaurantByZipCode.isEmpty()) {
                                    for (Restaurant restaurant : foundRestaurantByZipCode) {
                                        manager.displayRestaurantDetails(restaurant);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such restaurant with this zip code");
                                }
                                break;
                            case 6:
                                Set<String> categorySet = manager.foodCategories();
                                for(String cat: categorySet){
                                    List<String> restaurantName = manager.categoryWiseList(cat);

                                    System.out.print(cat+": ");
                                    for(String rname:restaurantName){
                                        System.out.print(rname);
                                    }
                                    System.out.println();
                                }

                                break;

                            case 7:
                                break;
                            default:
                                System.out.println("Invalid input.");
                            }
                    }while (choice!=7);


                    break;

                case 2:

                    do {

                        System.out.println("\nFood Item Searching Options: ");
                        System.out.println("1)By Name");
                        System.out.println("2)By Name in a Given Restaurant ");
                        System.out.println("3)By Category");
                        System.out.println("4)By Category in a Given Restaurant ");
                        System.out.println("5)By Price Range");
                        System.out.println("6)By Price Range in a Given Restaurant");
                        System.out.println("7)Costliest Food Item(s) on the Menu in a given Restaurant");
                        System.out.println("8)List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9)Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Enter food item name: ");
                                String name = scanner.nextLine();
                                List<Food> foundFoodByName = manager.searchFoodByName(name);
                                if (!foundFoodByName.isEmpty()) {
                                    for (Food food : foundFoodByName) {

                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food items with this name.");
                                }
                                break;
                            case 2:
                                System.out.print("Enter food item name: ");
                                String foodName = scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                String restaurantName = scanner.nextLine();
                                List<Food> foodFoundInRestaurant = manager.searchFoodByNameInRestaurant(foodName, restaurantName);
                                if (!foodFoundInRestaurant.isEmpty()) {
                                    for (Food food : foodFoundInRestaurant) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food item with this name on the menu of this restaurant");
                                }
                                break;
                            case 3:
                                System.out.print("Enter food item category: ");
                                String category = scanner.nextLine();
                                List<Food> foodFoundByCategory = manager.searchFoodByCategory(category);
                                if (!foodFoundByCategory.isEmpty()) {
                                    for (Food food : foodFoundByCategory) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food item with this category");
                                }
                                break;
                            case 4:
                                System.out.print("Enter food item category: ");
                                String foodCategory = scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                restaurantName = scanner.nextLine();

                                List<Food> categoryInRestaurant = manager.searchFoodByCategoryInRestaurant(foodCategory, restaurantName);
                                if (!categoryInRestaurant.isEmpty()) {
                                    for (Food food : categoryInRestaurant) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food item with this category on the menu of this restaurant");
                                }
                                break;
                            case 5:
                                System.out.print("Enter minimum price: ");
                                double minPrice = scanner.nextDouble();
                                System.out.print("Enter maximum price: ");
                                double maxPrice = scanner.nextDouble();
                                List<Food> foodFoundByPrice = manager.searchFoodByPriceRange(minPrice, maxPrice);
                                if (!foodFoundByPrice.isEmpty()) {
                                    for (Food food : foodFoundByPrice) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food item with this price range");
                                }
                                break;
                            case 6:
                                System.out.print("Enter minimum price: ");
                                minPrice = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter maximum price: ");
                                maxPrice = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                restaurantName = scanner.nextLine();
                                List<Food> priceRangeInRestaurant = manager.searchFoodByPriceRangeInRestaurant(minPrice, maxPrice, restaurantName);
                                if (!priceRangeInRestaurant.isEmpty()) {
                                    for (Food food : priceRangeInRestaurant) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                }
                                break;

                            case 7:
                                System.out.print("Enter restaurant name: ");
                                restaurantName = scanner.nextLine();
                                List<Food> costliestFoodItems = manager.costliestFoodItems(restaurantName);

                                if (!costliestFoodItems.isEmpty()) {
                                    for (Food food : costliestFoodItems) {
                                        manager.displayFoodDetails(food);
                                    }
                                }
                                else
                                {
                                    System.out.println("No costliest food item found for the specified restaurant.");
                                }
                                break;

                            case 8:
                                List<String> restaurantFoodCount = manager.restaurantFoodCount();

                                if (!restaurantFoodCount.isEmpty()) {
                                    for (String line : restaurantFoodCount) {
                                        System.out.println(line);
                                    }
                                }
                                else
                                {
                                    System.out.println("No restaurants with food items found.");
                                }
                                break;

                            case 9:
                                break;
                            default:
                                System.out.println("Invalid input!");
                        }
                    }while (choice!=9);


                    break;

                case 3:

                    //System.out.print("Enter restaurant Id: ");
                    int id = manager.restaurantCount() + 1;
                    //scanner.nextLine();
                    System.out.print("Enter restaurant name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter score: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    List<String> categories = new ArrayList<>();
                    for (int i = 1; i <= 3; i++) {
                        System.out.print("Enter category " + i + " : ");
                        String category = scanner.nextLine();
                        if (!category.isEmpty()) {
                            categories.add(category);
                        }
                        else{
                            break;
                        }
                    }

                    System.out.print("Enter price: ");
                    String price = scanner.nextLine();

                    System.out.print("Enter zip code: ");
                    String zipCode = scanner.nextLine();

                    //manager.addRestaurant(id, name, score, price, zipCode, categories);

                    break;

                case 4:

                    System.out.print("Enter restaurant name: ");
                    String restaurantName = scanner.nextLine();

                    Restaurant restaurant = manager.getRestaurantByName(restaurantName);
                    if (restaurant == null) {
                        System.out.println("Restaurant is not in the list!");
                        break;
                    }

                    System.out.print("Enter food item name: ");
                    String foodName = scanner.nextLine();

                    System.out.print("Enter food item category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter food item price: ");
                    double foodPrice = scanner.nextDouble();
                    scanner.nextLine();

                    manager.addFood(restaurant.getId(), category, foodName, foodPrice);

                    break;

                case 5:
                    manager.printingInMenu();
                    manager.printingInRestaurant();
                    System.out.println("Program Terminated!");
                    return;
                default:
                    System.out.println("Invalid input!");
            }

        }while (option != 5) ;

    }
}