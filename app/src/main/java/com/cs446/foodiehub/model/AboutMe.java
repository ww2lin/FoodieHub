package com.cs446.foodiehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-06-25.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AboutMe extends BaseModel{

    @JsonProperty("provider")
    String provider;

    @JsonProperty("email")
    String email;

    @JsonProperty("balance")
    String balance;

    @JsonProperty("role")
    String role;

    public AboutMe(){}


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
