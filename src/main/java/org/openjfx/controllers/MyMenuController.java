package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openjfx.model.ClientHolder;
import org.openjfx.model.Manager;
import org.openjfx.model.ManagerHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class MyMenuController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button addNewDish;
    @FXML
    private VBox chooseDish;
    @FXML
    private Label dishName;
    @FXML
    private Label dishPrice;
    @FXML
    private ImageView dishImage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridDishes;

    private Scene scene;
    private Manager user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManagerHolder client = ManagerHolder.getInstance();
        user = client.getManager();
    }

    @FXML
    public void backToManagerPage() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
            scene = new Scene(loadFXML("managerPage"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddDish() {
        try {
            Stage stage = (Stage) addNewDish.getScene().getWindow();
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
            scene = new Scene(loadFXML("addDish"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
