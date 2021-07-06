package org.openjfx.model;

import org.dizitart.no2.objects.Id;

public class Order {

    @Id
    private String id;
    private String nameOfDish;
    private String nameOfClient;
    private String price;
    private String nameOfManager;
    private int noOfDishes;

    public Order(String id, String nameOfDish, String nameOfClient, String price, String nameOfManager, int noOfDishes) {
        this.id = id;
        this.nameOfDish = nameOfDish;
        this.nameOfClient = nameOfClient;
        this.price = price;
        this.nameOfManager = nameOfManager;
        this.noOfDishes = noOfDishes;
    }

    public Order() {}
}
