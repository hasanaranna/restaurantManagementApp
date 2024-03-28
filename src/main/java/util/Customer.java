package util;

import java.util.ArrayList;

public class Customer implements java.io.Serializable {
    //private int id;
    private String name;
    ArrayList<Order> Orders;

    public Customer(String name) {
        //this.id = id;
        this.name = name;
        Orders = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        Orders.add(order);
    }

    /*public void removeOrder(Order order) {
        myOrders.remove(order);
    }*/

    public void setOrders(ArrayList<Order> orders) {
        Orders = orders;
    }

    /*public int getId() {
        return id;
    }*/

    public String getName() {
        return name;
    }

    public ArrayList<Order> getOrders() {
        return Orders;
    }

}
