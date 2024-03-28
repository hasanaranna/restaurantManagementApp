package Threads;

import com.example.demotester.restaurantHomeController;
import server.Manager;
import util.*;

import java.util.ArrayList;
import java.util.List;

public class readThreadRestaurant implements Runnable {

    private Thread thr;
    private NetworkUtil networkUtil;
    private Manager manager;
    private List<orderShowForRestaurant> orderList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    restaurantHomeController restauranthomecontroller;
    String name;

    public readThreadRestaurant(NetworkUtil networkUtil,Manager manager, String name,
                                restaurantHomeController restauranthomecontroller) {
        this.networkUtil = networkUtil;
        this.name = name;
        this.manager = manager;
        this.restauranthomecontroller = restauranthomecontroller;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            //changed code
            networkUtil.write(new clientToServerIntroduction(name, "restaurant"));

            while (true) {
                System.out.println("Waiting For Orders!");
                Object object = networkUtil.read();
                if(object instanceof Order)
                {
                    System.out.println("Order arrived!");
                    Order order = (Order) object;
                    //String userName = order.getCustomerName();
                    //List<orderShowForRestaurant> orderList = new ArrayList<>();
                    foodList = order.getFood();
                    for(Food food: foodList){
                        orderList.add(new orderShowForRestaurant(food.getName(), order.getCustomerName(),
                                food.getPrice(), food.getCategory()));
                        System.out.println(food.getName());
                    }
                    restauranthomecontroller.showOrder(orderList);
                    orderList.clear();
                    foodList.clear();
                }
                else if (object instanceof savedOrder) {
                    System.out.println("Previous order shown!");
                    savedOrder savedorder = (savedOrder) object;
                    //String userName = order.getCustomerName();
                    //List<orderShowForRestaurant> orderList = new ArrayList<>();
                    Order order = savedorder.getOrder();
                    foodList = order.getFood();
                    for(Food food: foodList){
                        orderList.add(new orderShowForRestaurant(food.getName(), order.getCustomerName(),
                                food.getPrice(), food.getCategory()));
                        System.out.println(food.getName());
                    }
                    restauranthomecontroller.showOrder(orderList);
                    orderList.clear();
                    foodList.clear();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

