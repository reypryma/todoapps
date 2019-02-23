package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController {

    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUser;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(event -> {

            String loginText = loginUser.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);


            ResultSet userRow = databaseHandler.getUser(user);

            int counter = 0;

            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    userId = userRow.getInt("userid");

                    System.out.println("Welcome! " + name);

                }
                if (counter == 1) {
                    showAddItem();
                } else {
                    Shaker userNameShaker = new Shaker(loginButton);
                    Shaker passwordShaker = new Shaker(loginPassword);
                    passwordShaker.shake();
                    userNameShaker.shake();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


        loginSignupButton.setOnAction(event -> {

            loginSignupButton.getScene().getWindow().hide();

            FXMLLoader loaderSignupButton = new FXMLLoader();

            loaderSignupButton.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loaderSignupButton.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent rootSignupButton = loaderSignupButton.getRoot();
            Stage stageSignupButton = new Stage();

            stageSignupButton.setScene(new Scene(rootSignupButton));

            stageSignupButton.showAndWait();

/*            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();*/


        });

    }

    private void showAddItem() {
        //Take users to AddItem screen
        loginSignupButton.getScene().getWindow().hide();

        FXMLLoader loadershowAddItem = new FXMLLoader();

        loadershowAddItem.setLocation(getClass().getResource("/sample/view/addItem.fxml"));

        try {
            loadershowAddItem.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Parent rootshowAddItem = loadershowAddItem.getRoot();
        Stage stageshowAddItem = new Stage();
        stageshowAddItem.setScene(new Scene(rootshowAddItem));

        AddItemController addItemController = loadershowAddItem.getController();
        addItemController.setUserId(userId);

        stageshowAddItem.show();


    }


}
