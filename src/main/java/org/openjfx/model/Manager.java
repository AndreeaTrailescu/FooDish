package org.openjfx.model;

public class Manager extends User {

    private String nameOfRestaurant;
    private String location;

    public Manager(String username, String password, String eMail, String role, String country, String town, String nameOfRestaurant, String location) {
        super(username,password,eMail,role,country,town);
        this.nameOfRestaurant = nameOfRestaurant;
        this.location = location;
    }
}
