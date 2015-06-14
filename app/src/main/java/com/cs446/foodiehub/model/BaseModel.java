package com.cs446.foodiehub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-06-14.
 */
public class BaseModel {
    @JsonProperty("_id")
    String id;
    @JsonProperty("name")
    String name;

    public BaseModel() {
    }

    public BaseModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
