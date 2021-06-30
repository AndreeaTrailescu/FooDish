package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.openjfx.model.ClientHolder;
import org.openjfx.model.Manager;
import org.openjfx.model.ManagerHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class ManagerHomePageController implements Initializable {

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
    private Manager user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManagerHolder client = ManagerHolder.getInstance();
        user = client.getManager();
    }

    @FXML
    public void handleMyMenu() {
        try {
            Stage stage = (Stage) myMenu.getScene().getWindow();
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
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
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
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
