package org.openjfx.model;

public class ManagerHolder {

    private Manager manager;
    private final static ManagerHolder INSTANCE = new ManagerHolder();

    private ManagerHolder() {}

    public static ManagerHolder getInstance() {
        return INSTANCE;
    }

    public void setManager(Manager m) {
        this.manager = m;
    }

    public Manager getManager() {
        return this.manager;
    }
}
