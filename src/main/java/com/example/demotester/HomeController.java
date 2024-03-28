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
import util.Food;
import util.searchCriteria;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {
    private Client client;
    private int choiceOne;
    private int choiceTwo;
    @FXML
    private TableColumn<Food, String> categoryColumn;


    @FXML
    private TableView<Food> searchTable;

    @FXML
    private TableColumn<Food, String> nameColumn;

    @FXML
    private TableColumn<Food, String> priceColumn;

    @FXML
    private TableColumn<Food, Integer> restaurantColumn;

    @FXML
    private ChoiceBox<String > firstChoiceBox = new ChoiceBox<>();

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;

    @FXML
    private ChoiceBox<String > secondChoiceBox = new ChoiceBox<>();

    @FXML
    private TextField textField;
    @FXML
    private Button search;
    @FXML
    private Button logOut;
    private customerClient customerclient;
    private List<Food> orderList = new ArrayList<>();
    @FXML
    void logOutPressed(ActionEvent event) throws Exception {
        client.showLoginPage();
    }

    @FXML
    void viewCartPressed(ActionEvent event) throws Exception {
        for(Food food: orderList){
            System.out.println(food.getName());
        }
        customerclient.orderFood(orderList);
        orderList.clear();
    }

    @FXML
    void searchPressed(ActionEvent event) {
        Manager manager = customerclient.getManager();
        //manager.displayAllFoodDetails();

        String searchByWhat = textField.getText();

        ObservableList<Food> foodItems = FXCollections.observableArrayList();
        searchCriteria searchBy = new searchCriteria();
        if(choiceOne == 2 && choiceTwo == 1)
        {
            searchBy.setName(searchByWhat);
            foodItems = FXCollections.observableArrayList(customerclient.showCustomerHome(2, 1, searchBy));
        }
        else if(choiceOne == 2 && choiceTwo == 3)
        {
            searchBy.setCategory(searchByWhat);
            foodItems = FXCollections.observableArrayList(customerclient.showCustomerHome(2, 3, searchBy));
        }
        else if(choiceOne == 1 && choiceTwo == 1)
        {
            searchBy.setName(searchByWhat);
            customerclient.showCustomerHome(1, 1, searchBy);
        }
        else if(choiceOne == 1 && choiceTwo == 4)
        {
            searchBy.setPrice(searchByWhat);
            customerclient.showCustomerHome(1, 4, searchBy);
        }
        else if(choiceOne == 1 && choiceTwo == 5)
        {
            searchBy.setZipCode(searchByWhat);
            customerclient.showCustomerHome(1, 5, searchBy);
        }
        else if(choiceOne == 2 && choiceTwo == 5)
        {
            double searchMinPrice = Double.parseDouble(minPrice.getText());
            double searchMaxPrice = Double.parseDouble(maxPrice.getText());
            searchBy.setMaxPrice(searchMaxPrice);
            searchBy.setMinPrice(searchMinPrice);
            foodItems = FXCollections.observableArrayList(customerclient.showCustomerHome(2, 5, searchBy));
        }

        // Set up the column cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("price"));
        restaurantColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("restaurantId"));
        searchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        searchTable.setItems(foodItems);

        System.out.println("search Pressed!");

    }
    private String[] firstChoiceString = {"Food"};
    private String[] secondChoiceString = {"Name", "Category", "Price Range"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> firstChoice = FXCollections.observableArrayList();
        for (String s : firstChoiceString) {
            firstChoice.add(s);
        }
        firstChoiceBox.setItems(firstChoice);
        //firstChoiceBox.setValue("Food");
        firstChoiceBox.setOnAction(this::getFirstChoice);
        //next choice box
        ObservableList<String> secondChoice = FXCollections.observableArrayList();
        for (String s : secondChoiceString) {
            secondChoice.add(s);
        }
        secondChoiceBox.setItems(secondChoice);
        //secondChoiceBox.setValue("Name");
        secondChoiceBox.setOnAction(this::getSecondChoice);
        //searchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        searchTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Food>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    List<Food> selectedItem = (List<Food>) change.getAddedSubList();
                    // You can store or display the selected items as needed
                    for(Food food: selectedItem)
                    {
                        orderList.add(food);
                    }
                }
            }
        });
    }
    private void getFirstChoice(ActionEvent event)
    {
        maxPrice.setVisible(false);
        minPrice.setVisible(false);
        String selectedOption = firstChoiceBox.getValue();
        if(selectedOption == "Restaurant"){
            choiceOne = 1;
            //System.out.println("1");
        }
        else{
            choiceOne = 2;
            //System.out.println("2");
        }
        //System.out.println("1 korlam");
    }
    private void getSecondChoice(ActionEvent event)
    {
        String selectedOption = secondChoiceBox.getValue();
        if(selectedOption == "Name"){
            choiceTwo = 1;
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
        }
        else if(selectedOption == "Score"){
            choiceTwo = 2;
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
            System.out.println("2");
        }
        else if(selectedOption == "Category"){
            choiceTwo = 3;
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
        }
        else if(selectedOption == "Price"){
            choiceTwo = 4;
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
            System.out.println("4");
        }
        else if(selectedOption == "ZipCode"){
            choiceTwo = 5;
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
            System.out.println("res er 5");
        }
        else if(selectedOption == "Price Range"){
            maxPrice.setVisible(true);
            minPrice.setVisible(true);
            choiceTwo = 5;
        }
    }
    void setMain(Client main, customerClient customerclient) {
        this.client = main;
        this.customerclient = customerclient;
    }

}
