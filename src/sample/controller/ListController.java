package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ListController {

    @FXML
    private ImageView listRefreshButton;

    @FXML
    private JFXListView<Task> listTask;
    @FXML
    public JFXTextField listTaskField;
    @FXML
    public JFXTextField listDescriptionField;
    @FXML
    public JFXButton listSaveTaskButton;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;

    private DatabaseHandler databaseHandler;


    void initialize() {
        /*listTask.setItems(listview);
        listTask.setCellFactory(param -> new JFXCell());*/
        Task myTask = new Task();
        myTask.setTask("Clean Car");
        myTask.setDescription("Have to cleaning");
        myTask.setDatecreated(Timestamp.valueOf(LocalDateTime.now()));

        tasks = FXCollections.observableArrayList();

        tasks.add(myTask);

        Task myTask2 = new Task();
        myTask2.setTask("Clean Home");
        myTask2.setDescription("Have to sweeping");
        myTask2.setDatecreated(Timestamp.valueOf(LocalDateTime.now()));


        listTask.setItems(tasks);
        //Setting it to the created customized cell
        listTask.setCellFactory(CellController -> new CellController());

        tasks = FXCollections.observableArrayList();

        tasks.addAll(myTask, myTask2);


    }

/*    void initialize() throws SQLException {
        System.out.println("initialize called");

        tasks = FXCollections.observableArrayList();


        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);



    }*/

}


