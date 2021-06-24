package org.openjfx.exceptions;

public class IncorrectLoginException extends Exception {
    private String password;

    public IncorrectLoginException (String password) {
        super(String.format("The user doesn't exist!"));
        this.password = password;
    }
}
