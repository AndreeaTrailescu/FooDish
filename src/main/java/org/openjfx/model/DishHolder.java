package org.openjfx.model;

public class DishHolder {
    private Dish dish;
    private final static DishHolder INSTANCE = new DishHolder();

    private DishHolder() {}

    public static DishHolder getInstance() {
        return INSTANCE;
    }

    public void setDish(Dish d) {
        this.dish = d;
    }

    public Dish getDish() {
        return this.dish;
    }
}
