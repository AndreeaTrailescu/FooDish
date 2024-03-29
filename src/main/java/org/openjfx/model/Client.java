package org.openjfx.model;

import org.dizitart.no2.objects.Id;

public class Client {

    @Id
    private String username;
    private String password;
    private String role;
    private String eMail;
    private String country;
    private String town;

    public Client(String username, String password, String eMail, String role, String country, String town) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.eMail = eMail;
        this.country = country;
        this.town = town;
    }

    public Client() {}

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }
}
