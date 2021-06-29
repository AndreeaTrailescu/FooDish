package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.openjfx.model.User;
import org.openjfx.model.UserHolder;

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
        UserHolder client = UserHolder.getInstance();
        User c = client.getUser();
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
