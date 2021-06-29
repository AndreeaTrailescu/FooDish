package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static org.openjfx.App.loadFXML;

public class MyMenuController {

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

    @FXML
    public void backToManagerPage() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
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
