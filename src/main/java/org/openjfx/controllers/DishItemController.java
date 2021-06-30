package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.listeners.DishListListener;
import org.openjfx.model.Dish;
import java.io.File;
import java.net.MalformedURLException;

public class DishItemController {

    @FXML
    private Label dishName;
    @FXML
    private Label dishPrice;
    @FXML
    private ImageView dishImage;

    @FXML
    public void click() throws MalformedURLException {
        dishListListener.onClickListener(dish);
    }

    private Dish dish;
    private DishListListener dishListListener;

    public void setDish(Dish dish, DishListListener dishListListener) throws MalformedURLException {
        this.dish = dish;
        this.dishListListener = dishListListener;
        dishName.setText(dish.getNameOfDish());
        dishPrice.setText(dish.getPrice());
        File file = new File(dish.getPhotoPath());
        Image image = new Image(file.toURI().toURL().toExternalForm(),false);
        dishImage.setImage(image);
    }

}
