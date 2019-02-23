package sample.controller;


import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {
    public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label notTaskLabel;

    @FXML
    private ImageView addButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {

            addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Shaker buttonshaker = new Shaker(addButton);
                buttonshaker.shake();

                FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
                FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), notTaskLabel);

                //Remove
                System.out.println("Clicked");
                addButton.relocate(0,5);
                notTaskLabel.relocate(0,70);

                addButton.setOpacity(0);
                notTaskLabel.setOpacity(0);

               fadeTransition.setFromValue(1f);
                fadeTransition.setToValue(0f);
                fadeTransition.setCycleCount(2);
                fadeTransition.setAutoReverse(false);
                fadeTransition.play();


                labelTransition.setFromValue(1f);
                labelTransition.setToValue(0f);
                labelTransition.setCycleCount(2);
                labelTransition.setAutoReverse(false);
                labelTransition.play();

                try {
                    AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                    FXMLLoader loader = new FXMLLoader();

                    //Add item and show it
                    loader.setLocation(getClass().getResource("/sample/view/addItemForm.fxml"));
                    //AddItemFormController addItemFormController = new AddItemFormController();
                    //addItemFormController.setUserId(getUserId());

                    AddItemController.userId = getUserId();


                    FadeTransition rootTransition = new FadeTransition(Duration.millis(1000), formPane);
                    rootTransition.setFromValue(0f);
                    rootTransition.setToValue(1f);
                    rootTransition.setCycleCount(1);
                    rootTransition.setAutoReverse(false);
                    rootTransition.play();


                    rootPane.getChildren().setAll(formPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("User Id is " + this.userId);

    }

    public int getUserId(){
        return this.userId;
    }
}
