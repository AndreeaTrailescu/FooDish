package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static org.openjfx.App.loadFXML;


public class AddDishController {

    @FXML
    private Button saveButton;
    @FXML
    private Button loadPhoto;
    @FXML
    private Button back;
    @FXML
    private TextField nameOfDish;
    @FXML
    private TextField price;
    @FXML
    private TextField ingredients;

    private Scene scene;

    @FXML
    public void backToMyMenu() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            scene = new Scene(loadFXML("myMenu"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
