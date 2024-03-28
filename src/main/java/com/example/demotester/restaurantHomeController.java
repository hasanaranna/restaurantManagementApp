package com.example.demotester;
//import javafx.controls;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.Manager;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class restaurantHomeController {
    private Client client;
    private restaurantClient restaurantclient;
    @FXML
    private Label restaurantNameLabel;
    @FXML
    private TableColumn<orderShowForRestaurant, String> categoryColumn;
    @FXML
    private TableView<orderShowForRestaurant> searchTable;
    @FXML
    private Button logOut;
    @FXML
    private TableColumn<orderShowForRestaurant, String> nameColumn;

    @FXML
    private TableColumn<orderShowForRestaurant, Double> priceColumn;

    @FXML
    private TableColumn<orderShowForRestaurant, String> customerColumn;
    @FXML
    private Button refresh;
    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField priceField;
    private NetworkUtil networkUtil;
    @FXML
    private Button addFood;
    private ObservableList<orderShowForRestaurant> foodItems = FXCollections.observableArrayList();
    private String restaurantName;
    public void init(String userName)
    {
        this.restaurantName = userName;
        restaurantNameLabel.setText(userName);
    }
    @FXML
    private Button serveOrder;
    @FXML
    private Button menu;
    @FXML
    void menuPressed(ActionEvent event) throws IOException {
        client.showMenu();
    }
    @FXML
    void serveOrderPressed(ActionEvent event) {
        Manager manager = client.getManager();
        manager.reloadOrderList();
        foodItems.clear();
    }
    @FXML
    void logOutPressed(ActionEvent event) throws Exception {
        client.showLoginPage();
    }
    @FXML
    void addFoodPressed(ActionEvent event) throws IOException, ClassNotFoundException {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String category = categoryField.getText();
        Manager manager = client.getManager();
        System.out.println(client.getUserName());
        int id = manager.getRestaurantIdByName(client.getUserName());
        manager.addNewFood(id , category,name,price);
        System.out.println(restaurantName+" new food");
        networkUtil.write(new newFood(client.getUserName(), "NEW FOOD"));
        //System.out.println("new food added");
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
    }

    @FXML
    void refreshPressed(ActionEvent event) throws IOException {
        foodItems.clear();
        List<orderShowForRestaurant> savedOrder = new ArrayList<>();
        Manager manager = client.getManager();
        manager.reloadOrderList();
        manager.readFiles();
        List<singleOrder> orderFoodList = manager.getOrderFoodList();
        for(singleOrder order: orderFoodList)
        {
            if(order.getRestaurantName().equals(restaurantName)){
                savedOrder.add(new orderShowForRestaurant(order.getName(), order.getCustomerName(),
                        order.getPrice(), order.getCategory()));
            }
        }
        showOrder(savedOrder);

    }

    public void showOrder(List<orderShowForRestaurant> foodList){
        foodItems.clear();
        foodItems = FXCollections.observableArrayList(foodList);
        try{
            // Set up the column cell value factories
            nameColumn.setCellValueFactory(new PropertyValueFactory<orderShowForRestaurant, String>("foodName"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<orderShowForRestaurant, String>("category"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<orderShowForRestaurant, Double>("price"));
            customerColumn.setCellValueFactory(new PropertyValueFactory<orderShowForRestaurant, String>("customerName"));
            searchTable.setItems(foodItems);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void setMain(Client client, restaurantClient restaurantclient, NetworkUtil networkUtil) {
        this.restaurantclient = restaurantclient;
        this.client = client;
        this.networkUtil = networkUtil;
    }
}
