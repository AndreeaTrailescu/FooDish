package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.exceptions.IncorrectLoginException;
import org.openjfx.model.*;
import org.openjfx.services.ClientService;
import org.openjfx.services.ManagerService;

import java.io.IOException;

import static org.openjfx.App.loadFXML;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class Login {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text message;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    private static ObjectRepository<Manager> MANAGER_REPOSITORY;
    private static ObjectRepository<Client> CLIENT_REPOSITORY;
    private Scene scene;

    @FXML
    public void handleLogin() {
        try {
            boolean testClient = ClientService.checkUserDoesAlreadyExist(username.getText(), password.getText());

            if(testClient) {
                CLIENT_REPOSITORY = ClientService.getClientRepository();
                Client loggedClient = CLIENT_REPOSITORY.find(eq("username", username.getText())).firstOrDefault();

                if(loggedClient.getRole().equals("client")) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    ClientHolder holder = ClientHolder.getInstance();
                    holder.setClient(loggedClient);
                    scene = new Scene(loadFXML("clientPage"));
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                }
            } else {
                MANAGER_REPOSITORY = ManagerService.getManagerRepository();
                boolean testManager = ManagerService.checkUserDoesAlreadyExist(username.getText(), password.getText());

                if(testManager) {
                    Manager loggedManager = MANAGER_REPOSITORY.find(eq("username", username.getText())).firstOrDefault();

                    if(loggedManager.getRole().equals("manager")) {
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        ManagerHolder holder = ManagerHolder.getInstance();
                        holder.setManager(loggedManager);
                        scene = new Scene(loadFXML("managerPage"));
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IncorrectLoginException ee) {
            message.setText(ee.getMessage());
        }
    }

    @FXML
    public void handleSignUp() {
        try {
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            scene = new Scene(loadFXML("signUp"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
