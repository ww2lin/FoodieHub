package com.cs446.foodiehub.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

/**
 * Created by Alex on 15-06-09.
 */
public enum FragmentType {
    ORDER_REQUESTS("Order Requests"),
    RESTAURANT("Restaurants"),
    ORDER_HISTORY("Order History"),
    ABOUT_ME("About Me");

    String name;

    FragmentType(String name){
        this.name = name;
    }

    public Fragment getFramgnetByType(FragmentManager fragmentManager){
        return fragmentManager.findFragmentByTag(name);
    }

    public String getName() {
        return name;
    }
}
