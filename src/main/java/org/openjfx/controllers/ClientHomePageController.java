package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static org.openjfx.App.loadFXML;

public class ClientHomePageController {

    @FXML
    private Button viewMenu;
    @FXML
    private Button myOrders;
    @FXML
    private Button saved;
    @FXML
    private Button contact;
    @FXML
    private Button logout;

    private Scene scene;

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
