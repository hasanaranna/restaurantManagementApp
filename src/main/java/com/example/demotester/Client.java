package com.example.demotester;

//import java.util.Scanner;
import Threads.updateFileThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;
//import requests.*;
import server.Manager;
import util.*;

import java.io.IOException;

public class Client extends Application {
    Reply reply;
    private Stage stage = new Stage();
    NetworkUtil networkUtil;
    Customer customer;
    Restaurant restaurant;
    Manager manager;
    customerClient customerclient;
    restaurantClient restaurantclient;
    restaurantHomeController restauranthomecontroller;
    private String userName;
    //Admin admin;

    public Manager getManager() {
        return manager;
    }

    public String getUserName() {
        return userName;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        //System.out.println("Start");
        //networkUtil = new NetworkUtil("127.0.0.1", 35000);

        //new Client();
        showLoginPage();

    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        //stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        //System.out.println("showLoginPage");
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public Client() {
        try {
            networkUtil = new NetworkUtil("127.0.0.1", 35000);
            Object resManager = networkUtil.read();
            this.manager = (Manager) resManager;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showLogin(int choice, String username) throws Exception {
        if (choice == 1) {
            //System.out.println("Enter username: ");
            //String username = sc.nextLine();
            userName = username;
            customerclient = new customerClient(networkUtil, manager, username);
        } else {
            //System.out.println("Enter username: ");
            //String username = sc.nextLine();
            userName = username;
            //networkUtil.write("NEW RESTAURANT");
            restaurantclient = new restaurantClient(networkUtil, manager, username, restauranthomecontroller);
        }



    }
    public void showRegistrationPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registrationPage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        registrationController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this, manager);

        // Set the primary stage
        stage.setTitle("Registration Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        menuController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this, manager);

        // Set the primary stage
        stage.setTitle("Menu Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this, customerclient);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showRestaurantHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("restaurantHome.fxml"));
        Parent root = loader.load();
        // Loading the controller
        restaurantHomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this, restaurantclient, networkUtil);
        restauranthomecontroller = controller;

        // Set the primary stage
        stage.setTitle("Restaurant Home Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
        new Client();
    }
}

