package com.example.demotester;

import Threads.readThreadRestaurant;
import server.Manager;
import util.NetworkUtil;
import util.clientToServerIntroduction;

import java.io.IOException;


public class restaurantClient {
    public NetworkUtil networkUtil;
    public Manager manager;
    readThreadRestaurant readthreadrestaurant;
    restaurantHomeController restauranthomecontroller;
    public String userName;

    public restaurantClient(NetworkUtil networkUtil,Manager manager, String userName,
                            restaurantHomeController restauranthomecontroller) throws IOException, ClassNotFoundException {
        this.networkUtil = networkUtil;
        this.manager = manager;
        this.userName = userName;
        this.restauranthomecontroller = restauranthomecontroller;
        System.out.println("Welcome "+ userName);
        networkUtil.write(new clientToServerIntroduction(userName, "restaurant"));
        networkUtil.write("NEW RESTAURANT");
        readthreadrestaurant = new readThreadRestaurant(networkUtil, manager, userName, restauranthomecontroller);
    }

    public Manager getManager() {
        return manager;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public String getUserName() {
        return userName;
    }


}
