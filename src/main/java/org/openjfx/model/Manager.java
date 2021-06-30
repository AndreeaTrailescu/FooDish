package org.openjfx.model;

import org.dizitart.no2.objects.Id;

public class Manager {

    @Id
    private String username;
    private String password;
    private String role;
    private String eMail;
    private String country;
    private String town;
    private String nameOfRestaurant;
    private String location;

    public Manager(String username, String password, String eMail, String role, String country, String town, String nameOfRestaurant, String location) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.eMail = eMail;
        this.country = country;
        this.town = town;
        this.nameOfRestaurant = nameOfRestaurant;
        this.location = location;
    }

    public String getNameOfRestaurant() {
        return nameOfRestaurant;
    }

    public String getLocation() {
        return location;
    }

    public Manager() {}

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

}
