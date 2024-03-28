package com.example.demotester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.Manager;
import util.NetworkUtil;
import util.newFood;

import java.util.ArrayList;
import java.util.List;

public class registrationController {
    Client client;
    Manager manager;
    @FXML
    private TextField categoryFIeld;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField scoreField;

    @FXML
    private Button submit;

    @FXML
    private TextField zipCodeField;

    @FXML
    void submitPressed(ActionEvent event) throws Exception {
        String name = nameField.getText();
        double score = Double.parseDouble(scoreField.getText());
        String price = priceField.getText();
        String categories = categoryFIeld.getText();
        String zipCode = zipCodeField.getText();
        String []cats = categories.split(",");
        List<String>categoryList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            categoryList.add(cats[i]);
        }
        //manager.displayAllRestaurantDetails();
        manager.addNewRestaurant(name, score, price, zipCode, categoryList);
        //NetworkUtil networkUtil = client.getNetworkUtil();
        //networkUtil.write("NEW RESTAURANT");
        manager.readFiles();
        //manager.displayAllRestaurantDetails();
        client.showLogin(2, name);
        client.showRestaurantHomePage(name);
        nameField.setText("");
        categoryFIeld.setText("");
        priceField.setText("");
        zipCodeField.setText("");
        scoreField.setText("");
    }
    void setMain(Client client, Manager manager) {
        this.client = client;
        this.manager = manager;
    }
}
