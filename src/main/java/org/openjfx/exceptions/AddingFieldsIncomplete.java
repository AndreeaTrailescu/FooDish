package org.openjfx.exceptions;

public class AddingFieldsIncomplete extends Exception {

    public AddingFieldsIncomplete() {
        super(String.format("All fields must be completed!"));
    }
}
