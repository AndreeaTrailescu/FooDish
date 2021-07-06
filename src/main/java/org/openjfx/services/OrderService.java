package org.openjfx.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.model.Order;

import java.util.List;

public class OrderService {

    private static ObjectRepository<Order> ordersRepository;

    public static void initDatabase() {
        ordersRepository = DatabaseService.getDatabase().getRepository(Order.class);
    }

    public static void addOrder(String id, String nameOfDish, String nameOfClient, String price, String nameOfManager, int noOfDishes) {
        ordersRepository.insert(new Order(id, nameOfDish, nameOfClient, price, nameOfManager, noOfDishes));
    }

    public static List<Order> getAllOrders() {
        return ordersRepository.find().toList();
    }

    public static ObjectRepository<Order> getOrdersRepository() {
        return ordersRepository;
    }
}
