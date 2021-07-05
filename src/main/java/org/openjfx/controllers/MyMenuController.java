package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.openjfx.listeners.DishListListener;
import org.openjfx.model.Dish;
import org.openjfx.model.Manager;
import org.openjfx.model.ManagerHolder;
import org.openjfx.services.DishService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class MyMenuController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button addNewDish;
    @FXML
    private Label dishName;
    @FXML
    private Label dishPrice;
    @FXML
    private ImageView dishImage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridDishes;
    @FXML
    private TextField searchBar;
    @FXML
    private Button closeButton;

    private Scene scene;
    private Manager user;
    private ArrayList<Dish> dishesList = new ArrayList<>();
    private DishListListener dishListListener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int column = 0;
            int row = 1;
            ManagerHolder client = ManagerHolder.getInstance();
            user = client.getManager();
            dishListListener = new DishListListener() {
                @Override
                public void onClickListener(Dish dish) throws MalformedURLException {
                    setChosenDish(dish);
                }
            };

            for (Dish d : DishService.getAllDishes()) {
                if (d.getUsernameManager().equals(user.getUsername())) {
                    dishesList.add(d);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/openjfx/dishItem.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    DishItemController itemController = fxmlLoader.getController();
                    itemController.setDish(d,dishListListener);

                    if(column == 3) {
                        column = 0;
                        row++;
                    }
                    gridDishes.add(anchorPane,column++,row);
                    GridPane.setMargin(anchorPane,new Insets(20));

                    gridDishes.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridDishes.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridDishes.setMinWidth(Region.USE_PREF_SIZE);

                    gridDishes.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridDishes.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridDishes.setMinHeight(Region.USE_PREF_SIZE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToManagerPage() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
            scene = new Scene(loadFXML("managerPage"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddDish() {
        try {
            Stage stage = (Stage) addNewDish.getScene().getWindow();
            ManagerHolder holder = ManagerHolder.getInstance();
            holder.setManager(user);
            scene = new Scene(loadFXML("addDish"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchButtonPressed() {
        try {
            scrollPane.setVvalue(0);
            if(!searchBar.getText().isEmpty()) {
                gridDishes.getChildren().clear();

                int column = 0;
                int row = 1;
                for(Dish dish : DishService.getAllDishes()) {
                    if(dish.getUsernameManager().equals(user.getUsername())) {
                        if((dish.getNameOfDish().toLowerCase()).indexOf(searchBar.getText().toLowerCase()) == 0 || (dish.getNameOfDish().substring(dish.getNameOfDish().indexOf(" ") + 1)).indexOf(searchBar.getText()) == 0) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/openjfx/dishItem.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            DishItemController itemController = fxmlLoader.getController();
                            itemController.setDish(dish,dishListListener);

                            if(column == 3) {
                                column = 0;
                                row++;
                            }
                            gridDishes.add(anchorPane,column++,row);
                            GridPane.setMargin(anchorPane,new Insets(20));

                            gridDishes.setMinWidth(Region.USE_COMPUTED_SIZE);
                            gridDishes.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            gridDishes.setMinWidth(Region.USE_PREF_SIZE);

                            gridDishes.setMinHeight(Region.USE_COMPUTED_SIZE);
                            gridDishes.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            gridDishes.setMinHeight(Region.USE_PREF_SIZE);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void reloadScene() {
        try {
            int column = 0;
            int row = 1;
            dishListListener = new DishListListener() {
                @Override
                public void onClickListener(Dish dish) throws MalformedURLException {
                    setChosenDish(dish);
                }
            };

            for (Dish d : DishService.getAllDishes()) {
                if (d.getUsernameManager().equals(user.getUsername())) {
                    dishesList.add(d);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/openjfx/dishItem.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    DishItemController itemController = fxmlLoader.getController();
                    itemController.setDish(d,dishListListener);

                    if(column == 3) {
                        column = 0;
                        row++;
                    }
                    gridDishes.add(anchorPane,column++,row);
                    GridPane.setMargin(anchorPane,new Insets(20));

                    gridDishes.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridDishes.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridDishes.setMinWidth(Region.USE_PREF_SIZE);

                    gridDishes.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridDishes.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridDishes.setMinHeight(Region.USE_PREF_SIZE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void setChosenDish(Dish dish) throws MalformedURLException {
        dishName.setText(dish.getNameOfDish());
        dishPrice.setText(dish.getPrice());
        File file = new File(dish.getPhotoPath());
        Image image = new Image(file.toURI().toURL().toExternalForm(),false);
        dishImage.setImage(image);
    }
}
