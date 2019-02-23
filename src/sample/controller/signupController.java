package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class signupController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField SignUplastName;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXTextField SignUpfirstName;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXCheckBox signUpCheckBoxfemale;

    @FXML
    private JFXPasswordField signUpPass;

    @FXML
    private JFXButton signUpButton;


    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String name = SignUpfirstName.getText();
        String lastname = SignUplastName.getText();
        String username = signUpUsername.getText();
        String password = signUpPass.getText();
        String location = signUpLocation.getText();

        String gender = "";

        if(signUpCheckBoxfemale.isSelected()){
            gender = "female";
        }else gender = "male";

        User user = new User(name, lastname, username, password, location, gender);

        databaseHandler.signUpUser(user);


    }
}
