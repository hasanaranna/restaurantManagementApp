package com.example.demotester;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.Manager;
import util.Food;
import util.orderShowForRestaurant;

import java.util.ArrayList;
import java.util.List;

public class menuController {
    private Client client;
    private Manager manager;
    @FXML
    private Button back;
    @FXML
    private Button load;
    @FXML
    private TableColumn<Food, String> categoryColumn;

    @FXML
    private TableColumn<Food, String> nameColumn;
    @FXML
    private TableView<Food> searchTable;
    @FXML
    private TableColumn<Food, Integer> idColumn;
    @FXML
    private TableColumn<Food, Double> priceColumn;
    private ObservableList<Food> foodItems = FXCollections.observableArrayList();
    @FXML
    public void backPressed(ActionEvent event) throws Exception {
        client.showRestaurantHomePage(client.getUserName());
    }
    @FXML
    public void loadPressed(ActionEvent event) {
        showMenu();
    }

    public void showMenu(){
        //System.out.println("1");
        List<Food> foodList = new ArrayList<>();
        foodList = manager.getMenuListByName(client.getUserName());
        //System.out.println("2");
        foodItems = FXCollections.observableArrayList(foodList);
        try{
            // Set up the column cell value factories
            nameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("category"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
            idColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("restaurantId"));
            searchTable.setItems(foodItems);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void setMain(Client client, Manager manager) {
        this.manager= manager;
        this.client = client;
    }
}
