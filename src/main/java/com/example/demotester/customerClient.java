package com.example.demotester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Threads.updateFileThread;
import server.Manager;
import util.*;

public class customerClient {
    NetworkUtil networkUtil;
    //int id;
    Manager manager;
    String customerName;
    Reply reply;
    List<Food>orderList = new ArrayList<>();

    public Manager getManager() {
        return manager;
    }

    public customerClient(NetworkUtil networkUtil, Manager manager, String customerName) throws ClassNotFoundException, IOException {
        this.networkUtil = networkUtil;
        this.manager = manager;
        this.customerName = customerName;
        System.out.println("Welcome " + customerName);
        networkUtil.write(new clientToServerIntroduction(customerName, "client"));
        new updateFileThread(networkUtil, manager);
        //showCustomerHome();
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Reply getReply() {
        return reply;
    }

    public List<Food> showCustomerHome(int choice1, int choice2, searchCriteria seachby) {
        //pore thik korbe
        int i = 1;
        List<Food> foundFood = null;
        try {
            while (i == 1) {
                //System.out.println("1.Search Food");
                //System.out.println("2.Logout");
                //System.out.println("1)Search Restaurants");
                //System.out.println("2)Search Food Items");
                i = 2;
                Scanner scanner = new Scanner(System.in);
                //int choice = Integer.parseInt(scanner.nextLine());
                switch (choice1) {
                    case 1:

                        do {
                            /*System.out.println("Restaurant Searching Options: ");
                            System.out.println("1)By Name");
                            System.out.println("2)By Score");
                            System.out.println("3)By Category");
                            System.out.println("4)By Price");
                            System.out.println("5)By Zip Code");
                            System.out.println("6)Different Category Wise List of Restaurants");
                            System.out.println("7)Back to Main Menu");
                            System.out.println("8)Order Foods");
                            System.out.print("Enter your choice: ");

                            choice = scanner.nextInt();
                            scanner.nextLine();*/

                            switch (choice2) {

                                case 1:
                                    System.out.print("Enter restaurant name: ");
                                    //String name = scanner.nextLine();
                                    List<Restaurant> foundRestaurantByName = manager.searchRestaurantsByName(seachby.getName());
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
                                    } else {
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
                                    } else {
                                        System.out.println("No such restaurant with this category");
                                    }
                                    break;
                                case 4:
                                    System.out.print("Enter price($,$$ or $$$): ");
                                    //String price = scanner.nextLine();
                                    List<Restaurant> foundRestaurantByPrice = manager.searchRestaurantsByPrice(seachby.getPrice());
                                    if (!foundRestaurantByPrice.isEmpty()) {
                                        for (Restaurant restaurant : foundRestaurantByPrice) {
                                            manager.displayRestaurantDetails(restaurant);
                                        }
                                    } else {
                                        System.out.println("No such restaurant with this price");
                                    }
                                    break;
                                case 5:
                                    System.out.print("Enter Zip Code: ");
                                    //String zipCode = scanner.nextLine();
                                    List<Restaurant> foundRestaurantByZipCode = manager.searchRestaurantsByZipCode(seachby.getZipCode());
                                    if (!foundRestaurantByZipCode.isEmpty()) {
                                        for (Restaurant restaurant : foundRestaurantByZipCode) {
                                            manager.displayRestaurantDetails(restaurant);
                                        }
                                    } else {
                                        System.out.println("No such restaurant with this zip code");
                                    }
                                    break;
                                case 6:
                                    Set<String> categorySet = manager.foodCategories();
                                    for (String cat : categorySet) {
                                        List<String> restaurantName = manager.categoryWiseList(cat);

                                        System.out.print(cat + ": ");
                                        for (String rname : restaurantName) {
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
                        } while (choice2 != 7);
                        break;


                    case 2:

                        /*do {

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
                            System.out.println("10)Order Foods");
                            System.out.print("Enter your choice: ");

                            choice = scanner.nextInt();
                            scanner.nextLine();*/

                        switch (choice2) {
                            case 1:
                                //System.out.println("Enter food item name: ");
                                //String name = scanner.nextLine();
                                foundFood = manager.searchFoodByName(seachby.getName());
                                if(!foundFood.isEmpty()) return foundFood;
                                else{
                                    foundFood.add(new Food(0, "N/A", "N/A", 0.0));
                                    return foundFood;
                                }

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
                                } else {
                                    System.out.println("No such food item with this name on the menu of this restaurant");
                                }
                                break;
                            case 3:
                                //System.out.print("Enter food item category: ");
                                //String category = scanner.nextLine();
                                foundFood = manager.searchFoodByCategory(seachby.getCategory());
                                /*if (!foodFoundByCategory.isEmpty()) {
                                    for (Food food : foodFoundByCategory) {
                                        manager.displayFoodDetails(food);
                                    }
                                } else {
                                    System.out.println("No such food item with this category");
                                }*/
                                if(!foundFood.isEmpty()) return foundFood;
                                break;
                            case 4:
                                System.out.print("Enter food item category: ");
                                String foodCategory = scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                restaurantName = scanner.nextLine();

                                List<Food> categoryInRestaurant = manager.searchFoodByCategoryInRestaurant(foodCategory, restaurantName);
                                if (!categoryInRestaurant.isEmpty()) {
                                    return foundFood;
                                    }
                                else {
                                    System.out.println("No such food item with this category on the menu of this restaurant");
                                }
                                break;
                            case 5:
                                foundFood = manager.searchFoodByPriceRange(seachby.getMinPrice(), seachby.getMaxPrice());
                                if (!foundFood.isEmpty()) {
                                    return foundFood;
                                } else {
                                    System.out.println("No such food item with this price range");
                                }
                                break;
                            case 6:
                                /*System.out.print("Enter minimum price: ");
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
                                } else {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                }*/
                                break;

                            case 7:
                                System.out.print("Enter restaurant name: ");
                                restaurantName = scanner.nextLine();
                                List<Food> costliestFoodItems = manager.costliestFoodItems(restaurantName);

                                if (!costliestFoodItems.isEmpty()) {
                                    for (Food food : costliestFoodItems) {
                                        manager.displayFoodDetails(food);
                                    }
                                } else {
                                    System.out.println("No costliest food item found for the specified restaurant.");
                                }
                                break;

                            case 8:
                                List<String> restaurantFoodCount = manager.restaurantFoodCount();

                                if (!restaurantFoodCount.isEmpty()) {
                                    for (String line : restaurantFoodCount) {
                                        System.out.println(line);
                                    }
                                } else {
                                    System.out.println("No restaurants with food items found.");
                                }
                                break;

                            case 9:
                                break;

                            default:
                                System.out.println("Invalid input!");
                        }
                }
                while (choice2 != 9) ;


                break;
                /*if (choice == 1) {
                    System.out.println("Enter Food Name: ");
                    String foodName = sc.nextLine();

                    nu.write(new SearchFood(foodName, "name"));
                    isOrderCheckingRunning = false;
                    SendRequest SearchFoodThread = new SendRequest(this);
                    SearchFoodThread.join();

                    if (response != null && response.getMessage().equals("found")) {
                        List<Food> foods = (List) response.getData();

                        for (int i = 1; i <= foods.size(); i++)
                            System.out.println(i + ". " + foods.get(i - 1).getName());
                        showOrderFood(foods);
                    }

                }*/
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return foundFood;
    }

    public void orderFood(List<Food> orderListFromCustomer) throws Exception {

        orderList = orderListFromCustomer;
        Food first = orderList.get(0);
        int resID = first.getRestaurantId();
        String restaurant = manager.getRestaurantNameById(resID);

        //System.out.println("CC "+orderListFromCustomer.get(0).getName());
        Order neworder = new Order(customerName, restaurant, orderListFromCustomer);
        networkUtil.write(neworder);
        orderList.clear();
        System.out.println("Order Placed!");
    }


}
