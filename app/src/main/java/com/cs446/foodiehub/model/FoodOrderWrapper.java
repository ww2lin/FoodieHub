package com.cs446.foodiehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodOrderWrapper extends com.cs446.foodiehub.model.Order {
    @JsonProperty("items")
    ArrayList<FoodOrder> foodOrders;

    public FoodOrderWrapper(){}

    public ArrayList<FoodOrder> getFoodOrders() {
        return foodOrders;
    }
}
