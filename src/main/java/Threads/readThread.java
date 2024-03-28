package Threads;

import server.Manager;
import util.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class readThread implements Runnable {
    private Thread thr;
    private List<savedOrder> savedOrderList;
    private NetworkUtil networkUtil;
    private Order order;
    public HashMap<String, NetworkUtil> clientMap;
    private List<String> customerNames;
    public readThread(HashMap<String, NetworkUtil> map, NetworkUtil networkUtil, List<savedOrder> savedOrderList,
                      List<String> customerNames) {
        this.networkUtil = networkUtil;
        this.clientMap = map;
        this.savedOrderList = savedOrderList;
        this.customerNames = customerNames;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object object = networkUtil.read();
                //System.out.println("Customer has sent something!");
                if(object instanceof Order)
                {
                    System.out.println("Customer has placed an order!");
                    order = (Order) object;
                    String customerName = order.getCustomerName();
                    String restaurantName = order.getRestaurantName();
                    List<Food> foodList = order.getFood();
                    System.out.println(customerName+","+restaurantName);
                    System.out.println(foodList.get(0).getName());
                    NetworkUtil networkUtil1 = clientMap.get(restaurantName);
                    if(networkUtil1 == null){
                        try {
                            FileWriter fileWriter = new FileWriter("order.txt");

                            for(Food food: foodList){
                                String str = customerName+","+restaurantName+","+
                                        food.getRestaurantId()+","+food.getCategory()+","+
                                        food.getName()+","+food.getPrice()+"\n";
                                fileWriter.write(str);
                            }
                            fileWriter.close();
                            System.out.println("Content appended to the file.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        networkUtil1.write(order);
                    }
                }
                else if(object instanceof clientToServerIntroduction){
                    clientToServerIntroduction csi = (clientToServerIntroduction) object;
                    if(csi.getWhat().equals("restaurant")){
                        networkUtil.write(savedOrderList);
                    }
                }
                else if(object instanceof newFood)
                {
                    System.out.println("new food!");
                    newFood str = (newFood) object;
                    if(str.getFlag().equals("NEW FOOD")){
                        for(String customerName: customerNames){
                            NetworkUtil networkUtil1 = clientMap.get(customerName);
                            networkUtil1.write("NEW FOOD");
                        }
                    }
                }
                else if(object instanceof String){
                    String str = (String) object;
                    if(str.equals("NEW RESTAURANT")){
                        System.out.println("new res joined");
                        for(String customerName: customerNames){
                            NetworkUtil networkUtil1 = clientMap.get(customerName);
                            networkUtil1.write("NEW RESTAURANT");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


