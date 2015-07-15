package com.cs446.foodiehub.model.server;

import com.cs446.foodiehub.model.server.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-12.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecentOrder extends com.cs446.foodiehub.model.Order {
    @JsonProperty("items")
    ArrayList<MenuItem> menuItems;

    public RecentOrder(){}

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

}
