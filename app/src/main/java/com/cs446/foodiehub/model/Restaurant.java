package com.cs446.foodiehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-06-14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant extends BaseModel{

    @JsonProperty("restaurantid")
    String restaurantId;

    @JsonProperty("address")
    String address;

    public Restaurant() {}

    public Restaurant(String id, String name, String restaurantId, String address) {
        super(id, name);
        this.restaurantId = restaurantId;
        this.address = address;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
