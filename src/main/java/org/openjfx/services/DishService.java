package org.openjfx.services;

import javafx.scene.control.TextField;
import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.exceptions.AddingFieldsIncomplete;
import org.openjfx.model.Dish;

import java.util.List;

public class DishService {

    private static ObjectRepository<Dish> dishesRepository;

    public static void initDatabase() {
        dishesRepository = DatabaseService.getDatabase().getRepository(Dish.class);
    }

    public static void addDish(String id, String nameOfDish, String ingredients, String price, String restaurant, String usernameManager, String photoPath) {
        dishesRepository.insert(new Dish(id, nameOfDish, ingredients, price, restaurant, usernameManager, photoPath));
    }

    public static List<Dish> getAllDishes() {
        return dishesRepository.find().toList();
    }

    public static ObjectRepository<Dish> getDishesRepository() {
        return dishesRepository;
    }

    public static boolean testEveryFieldIsCompleted(TextField nameOfDish, TextField ingredients, TextField price, String imagePath) throws AddingFieldsIncomplete {
        if(imagePath != null && !nameOfDish.getText().isEmpty() && !price.getText().isEmpty() && !ingredients.getText().isEmpty()) return true;
        else throw new AddingFieldsIncomplete();
    }

}
