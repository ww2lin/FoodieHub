package com.cs446.foodiehub.model;

/**
 * Created by Alex on 15-07-13.
 */
public class FoodSection extends FoodItem {

    private boolean isSection = false;

    private String restaurantTitle;

    private String table;

    private String date;

    public static FoodSection generateSectionMenu(FoodOrderWrapper foodOrderWrapper){
        FoodSection foodSection = new FoodSection();
        foodSection.isSection = true;
        foodSection.restaurantTitle = foodOrderWrapper.getRestaurant().getName();
        foodSection.date = foodOrderWrapper.getDate();
        foodSection.table = foodOrderWrapper.getTableNumber();
        foodSection.orderId = foodOrderWrapper.getOrderId();
        return foodSection;
    }

    public static FoodSection generateSectionMenu(RecentOrder recentOrder){
        FoodSection foodSection = new FoodSection();
        foodSection.isSection = true;
        foodSection.restaurantTitle = recentOrder.getRestaurant().getName();
        foodSection.date = recentOrder.getDate();
        foodSection.orderId = recentOrder.getOrderId();
        return foodSection;
    }

    public FoodSection(){}

    public boolean isSection() {
        return isSection;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public String getDate() {
        return date;
    }

    public String getTable() {
        return table;
    }

    public String getOrderId() {
        return orderId;
    }
}
