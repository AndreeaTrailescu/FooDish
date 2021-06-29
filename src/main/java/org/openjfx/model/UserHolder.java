package org.openjfx.model;

public final class UserHolder {

    private User client;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User c) {
        this.client = c;
    }

    public User getUser() {
        return this.client;
    }
}
