package org.openjfx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.NitriteId;
import org.openjfx.listeners.DishListListener;
import org.openjfx.model.*;
import org.openjfx.services.DishService;
import org.openjfx.services.ManagerService;
import org.openjfx.services.OrderService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.App.loadFXML;

public class MenuController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label dishName;
    @FXML
    private Label dishPrice;
    @FXML
    private Label nameRestaurant;
    @FXML
    private ImageView dishImage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridDishes;
    @FXML
    private TextField searchBar;
    @FXML
    private Button orderButton;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Label totalPrice;
    @FXML
    private Text message;

    private Scene scene;
    private Client user;
    private ArrayList<Dish> dishesList = new ArrayList<>();
    private DishListListener dishListListener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dishesList = new ArrayList<>();
            initSpinner();
            spinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                    if(!dishPrice.getText().isEmpty())
                        totalPrice.setText(String.valueOf(spinner.getValue() * Double.parseDouble(dishPrice.getText())));
                }
            });

            int column = 0;
            int row = 1;
            ClientHolder client = ClientHolder.getInstance();
            user = client.getClient();
            dishListListener = new DishListListener() {
                @Override
                public void onClickListener(Dish dish) throws MalformedURLException {
                    setChosenDish(dish);
                }
            };

            for (Dish d : DishService.getAllDishes()) {
                for(Manager m : ManagerService.getAllManagers()) {
                    if(m.getUsername().equals(d.getUsernameManager()) && m.getTown().equals(user.getTown()) && m.getCountry().equals(user.getCountry())) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSpinner() {
        spinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0)
        );
        totalPrice.setText(String.valueOf(0));
    }

    @FXML
    public void searchButtonPressed() {
        try {
            scrollPane.setVvalue(0);
            if(!searchBar.getText().isEmpty()) {
                gridDishes.getChildren().clear();

                int column = 0;
                int row = 1;

                for (Dish d : DishService.getAllDishes()) {
                    for(Manager m : ManagerService.getAllManagers()) {
                        if(m.getUsername().equals(d.getUsernameManager()) && m.getTown().equals(user.getTown()) && m.getCountry().equals(user.getCountry())) {
                            if((d.getNameOfDish().toLowerCase()).indexOf(searchBar.getText().toLowerCase()) == 0 || (d.getNameOfDish().substring(d.getNameOfDish().indexOf(" ") + 1)).indexOf(searchBar.getText()) == 0) {
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
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reloadScene() {
        try {
            dishesList = new ArrayList<>();
            scrollPane.setVvalue(0);
            initSpinner();
            spinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                    if(!dishPrice.getText().isEmpty())
                        totalPrice.setText(String.valueOf(spinner.getValue() * Double.parseDouble(dishPrice.getText())));
                }
            });

            int column = 0;
            int row = 1;
            dishListListener = new DishListListener() {
                @Override
                public void onClickListener(Dish dish) throws MalformedURLException {
                    setChosenDish(dish);
                }
            };

            for (Dish d : DishService.getAllDishes()) {
                for(Manager m : ManagerService.getAllManagers()) {
                    if(m.getUsername().equals(d.getUsernameManager()) && m.getTown().equals(user.getTown()) && m.getCountry().equals(user.getCountry())) {
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setChosenDish(Dish dish) throws MalformedURLException {
        initSpinner();
        dishName.setText(dish.getNameOfDish());
        dishPrice.setText(dish.getPrice());
        nameRestaurant.setText(dish.getRestaurant());
        File file = new File(dish.getPhotoPath());
        Image image = new Image(file.toURI().toURL().toExternalForm(),false);
        dishImage.setImage(image);
    }

    @FXML
    public void backToClientPage() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            ClientHolder holder = ClientHolder.getInstance();
            holder.setClient(user);
            scene = new Scene(loadFXML("clientPage"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void takeDish() {
        for(Dish d : dishesList) {
            if(d.getNameOfDish().equals(dishName.getText()) && !dishName.getText().isEmpty()) {
                OrderService.addOrder(String.valueOf(NitriteId.newId()),dishName.getText(),user.getUsername(),totalPrice.getText(),d.getUsernameManager(),spinner.getValue());
                message.setText("Successfully ordered!");
            }
        }
    }
}

