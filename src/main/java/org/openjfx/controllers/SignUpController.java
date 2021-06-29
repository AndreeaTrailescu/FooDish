package org.openjfx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.openjfx.exceptions.IncorrectConfirmPassword;
import org.openjfx.exceptions.UsernameExists;
import org.openjfx.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class SignUpController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text message;
    @FXML
    private Text messageUsername;
    @FXML
    private TextField eMail;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField country;
    @FXML
    private TextField town;
    @FXML
    private CheckBox role;
    @FXML
    private TextField restaurant;
    @FXML
    private TextField location;
    @FXML
    private Button continueButton;
    @FXML
    private Button loginButton;

    private Scene scene;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!role.isSelected()) {
            restaurant.setDisable(true);
            location.setDisable(true);
        }
        role.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    restaurant.setDisable(false);
                    location.setDisable(false);
                } else {
                    restaurant.setDisable(true);
                    location.setDisable(true);
                }
            }
        });
    }

    @FXML
    public void backToStart() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
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
    public void handleContinue() {
        try {
            boolean testUsername = UserService.checkUsernameIsAvailable(username.getText());
            boolean testPassword = UserService.checkConfirmPasswordIsCorrect(password.getText() , confirmPassword.getText());
            boolean tickedCheckBox = role.isSelected();

            if(testUsername && testPassword) {

                if(tickedCheckBox) {
                    if(!restaurant.getText().isEmpty() && !location.getText().isEmpty()) {
                        UserService.addManager(username.getText(), password.getText(),eMail.getText(), "manager", country.getText(), town.getText(), restaurant.getText(), location.getText());
                        Stage stage = (Stage) continueButton.getScene().getWindow();
                        scene = new Scene(loadFXML("managerPage"));
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();
                    }

                } else {
                    UserService.addClient(username.getText(), password.getText(), eMail.getText(), "client", country.getText(), town.getText());
                    Stage stage = (Stage) continueButton.getScene().getWindow();
                    scene = new Scene(loadFXML("clientPage"));
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IncorrectConfirmPassword ee) {
            message.setText(ee.getMessage());
            messageUsername.setText("");
        } catch (UsernameExists eee) {
            message.setText("");
            messageUsername.setText(eee.getMessage());
        }
    }
}
