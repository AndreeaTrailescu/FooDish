package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.openjfx.model.Client;
import org.openjfx.model.ClientHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class ClientHomePageController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientHolder client = ClientHolder.getInstance();
        Client c = client.getClient();
    }

    @FXML
    public void handleViewMenu() {
        try {
            Stage stage = (Stage) viewMenu.getScene().getWindow();
            scene = new Scene(loadFXML("menuPage"));
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

    @FXML
    public void handleClose() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

}
