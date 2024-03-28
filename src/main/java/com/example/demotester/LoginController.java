package com.example.demotester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private Client client;
    private int who;
    @FXML
    private Button customerButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button restaurantButton;
    @FXML
    private Label prompt;
    @FXML
    private TextField userText;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signUp;
    @FXML
    void signUpPressed(ActionEvent event) throws IOException {
        client.showRegistrationPage();
    }
    @FXML
    void customerButtonPressed(ActionEvent event) {
        customerButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: white;");
        customerButton.setOnMousePressed(e -> {
            customerButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: red;");
        });
        customerButton.setOnMouseReleased(e -> {
            customerButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: white;");
        });
        who = 1;
        prompt.setVisible(false);
        signUp.setVisible(false);
    }

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordField.getText();
        //String password = passwordText.getText();
        if (!userName.equals(" ")) {
            try {
                if(who == 1){
                    client.showLogin(who, userName);
                    client.showHomePage(userName);
                }
                else{
                    client.showRestaurantHomePage(userName);
                    client.showLogin(who, userName);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void restaurantButtonPressed(ActionEvent event) {
        restaurantButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: white;");
        restaurantButton.setOnMousePressed(e -> {
            restaurantButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: red;");
        });
        restaurantButton.setOnMouseReleased(e -> {
            restaurantButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: white;");
        });
        who = 2;
        prompt.setVisible(true);
        signUp.setVisible(true);
    }
    void setMain(Client client) {

        this.client = client;
    }

}
