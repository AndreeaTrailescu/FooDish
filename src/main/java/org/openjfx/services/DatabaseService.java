package org.openjfx.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.model.Dish;
import org.openjfx.model.User;

import static org.openjfx.services.FileSystemService.getPathToFile;

public class DatabaseService {

    private static Nitrite database;
    private static ObjectRepository<User>  userRepository;
    private static ObjectRepository<Dish>  dishesRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("login-database.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        dishesRepository = database.getRepository(Dish.class);
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
