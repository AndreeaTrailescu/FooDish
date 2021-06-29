package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static org.openjfx.App.loadFXML;

public class ManagerHomePageController {

    @FXML
    private Button myMenu;
    @FXML
    private Button orders;
    @FXML
    private Button profile;
    @FXML
    private Button contact;
    @FXML
    private Button logout;

    private Scene scene;

    @FXML
    public void handleMyMenu() {
        try {
            Stage stage = (Stage) myMenu.getScene().getWindow();
            scene = new Scene(loadFXML("myMenu"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogout() {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            scene = new Scene(loadFXML("startPage"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
