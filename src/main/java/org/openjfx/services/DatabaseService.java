package org.openjfx.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.model.Client;
import org.openjfx.model.Dish;
import org.openjfx.model.Manager;
import org.openjfx.model.Order;

import static org.openjfx.services.FileSystemService.getPathToFile;

public class DatabaseService {

    private static Nitrite database;
    private static ObjectRepository<Client>  clientRepository;
    private static ObjectRepository<Manager>  managerRepository;
    private static ObjectRepository<Dish>  dishesRepository;
    private static ObjectRepository<Order>  ordersRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("login-database.db").toFile())
                .openOrCreate("test", "test");

        clientRepository = database.getRepository(Client.class);
        managerRepository = database.getRepository(Manager.class);
        dishesRepository = database.getRepository(Dish.class);
        ordersRepository = database.getRepository(Order.class);
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
