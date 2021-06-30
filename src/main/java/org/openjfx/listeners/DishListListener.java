package org.openjfx.listeners;

import org.openjfx.model.Dish;

import java.net.MalformedURLException;

public interface DishListListener {

    public void onClickListener(Dish dish) throws MalformedURLException;
}
