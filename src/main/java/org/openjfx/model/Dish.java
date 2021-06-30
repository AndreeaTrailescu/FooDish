package org.openjfx.model;

import org.dizitart.no2.objects.Id;

public class Dish {

    @Id
    private String id;
    private String nameOfDish;
    private String ingredients;
    private String price;
    private String restaurant;
    private String usernameManager;
    private String photoPath;

    public Dish(String id, String nameOfDish, String ingredients, String price, String restaurant, String usernameManager, String photoPath) {
        this.id = id;
        this.nameOfDish = nameOfDish;
        this.ingredients = ingredients;
        this.price = price;
        this.restaurant = restaurant;
        this.usernameManager = usernameManager;
        this.photoPath = photoPath;
    }

    public Dish() {}

    public String getUsernameManager() {
        return usernameManager;
    }

    public String getPrice() {
        return price;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }
}
