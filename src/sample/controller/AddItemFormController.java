package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {
    private DatabaseHandler databaseHandler;
    private int userId;


    @FXML
    private ResourceBundle resources;

    @FXML
    private Label successLabel;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private JFXButton todosButton;


    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();
        Task task = new Task();



        saveTaskButton.setOnAction(event -> {

            // 1. Kalender 2 Timestamp ke kalender serta perintah getTimeinMillis
            // 3 masukkan dateCreated ke task, timestamp ke dateCreated lalu fix it
            //masukkan insert task ke database
            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {

                System.out.println("Userid " + AddItemController.userId);

                task.setUserId(AddItemController.userId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                databaseHandler.insertTask(task);

                todosButton.setVisible(true);
                int tasknumber = 0;
                try {
                    tasknumber = databaseHandler.getAllTasks(AddItemController.userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                todosButton.setText("My todo's : " + "(" + tasknumber + ")");
                todosButton.setOnAction(event1 -> {
                   //send users to the list screen



                });
                taskField.setText("");
                descriptionField.setText("");
                successLabel.setVisible(true);
            }


        });

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println(this.userId);
    }


}
