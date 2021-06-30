package org.openjfx.controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dizitart.no2.NitriteId;
import org.openjfx.exceptions.AddingFieldsIncomplete;
import org.openjfx.model.Manager;
import org.openjfx.model.ManagerHolder;
import org.openjfx.services.DishService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;


public class AddDishController implements Initializable {

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
    @FXML
    private ImageView imageDish;
    @FXML
    private Text message;
    private String imagePath;
    private File file;


    private Scene scene;
    private Manager user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManagerHolder client = ManagerHolder.getInstance();
        user = client.getManager();
    }

    @FXML
    public void loadAddPhoto() throws MalformedURLException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("c:/"));
        FileChooser.ExtensionFilter extJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");

        fileChooser.getExtensionFilters().addAll(extJPG,extPNG);
        file = fileChooser.showOpenDialog(stage);
        imagePath = file.getAbsolutePath();
        fileChooser.setInitialDirectory(file.getParentFile());
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toURL().toExternalForm(),false);
        imageDish.setImage(image);
    }

    @FXML
    public void backToMyMenu() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
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
    public void handleSaveDish() {
        try {
            boolean test = DishService.testEveryFieldIsCompleted(nameOfDish, ingredients, price, imagePath);

            if(test) {
                DishService.addDish(NitriteId.newId().toString(), nameOfDish.getText(), ingredients.getText(), price.getText(), user.getNameOfRestaurant(), user.getUsername(), imagePath);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                ManagerHolder holder = ManagerHolder.getInstance();
                holder.setManager(user);
                scene = new Scene(loadFXML("myMenu"));
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddingFieldsIncomplete ee) {
            message.setText(ee.getMessage());
        }
    }
}
