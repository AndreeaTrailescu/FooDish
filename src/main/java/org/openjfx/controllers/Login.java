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
import org.openjfx.services.UserService;

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

    private static ObjectRepository<User> USER_REPOSITORY;
    private Scene scene;

    @FXML
    public void handleLogin() {
        USER_REPOSITORY = UserService.getUserRepository();
        try {
            boolean testUser = UserService.checkUserDoesAlreadyExist(username.getText(), password.getText());

            if(testUser) {

                User loggedUser = USER_REPOSITORY.find(eq("username", username.getText())).firstOrDefault();

                if(loggedUser.getRole().equals("client")) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(loggedUser);
                    scene = new Scene(loadFXML("clientPage"));
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } else {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(loggedUser);
                    scene = new Scene(loadFXML("managerPage"));
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
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
