package com.cs446.foodiehub.model.server;

/**
 * Created by Alex on 15-07-14.
 */
public enum FoodStatus {

    PROGRESS("Progress"),
    COMPLETED("Completed"),
    FAILED("Failed");

    String name;

    FoodStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
