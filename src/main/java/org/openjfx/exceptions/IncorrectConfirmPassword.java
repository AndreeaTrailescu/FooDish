package org.openjfx.exceptions;

public class IncorrectConfirmPassword extends Exception {
    private String password;

    public IncorrectConfirmPassword (String password) {
        super(String.format("Incorrect password!"));
        this.password = password;
    }
}
