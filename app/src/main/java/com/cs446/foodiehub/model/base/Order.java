package com.cs446.foodiehub.model;

import com.cs446.foodiehub.model.server.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-07-12.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    // this is the order id to be use to update the status
    @JsonProperty("_id")
    String id;
    @JsonProperty("taxamount")
    String taxAmount;
    @JsonProperty("total")
    String total;
    @JsonProperty("subtotal")
    String subTotal;
    @JsonProperty("tablenumber")
    String tableNumber;
    @JsonProperty("tax")
    String tax;
    @JsonProperty("tip")
    String tip;
    @JsonProperty("date")
    String date;

    @JsonProperty("restaurant")
    Restaurant restaurant;

    public Order(){}

    public String getId() {
        return id;
    }

    public String getOrderId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
