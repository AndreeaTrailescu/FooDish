package org.openjfx.exceptions;

public class UsernameExists extends Exception {
    private String username;

    public UsernameExists (String username) {
        super(String.format("Username already exists!"));
        this.username = username;
    }
}
