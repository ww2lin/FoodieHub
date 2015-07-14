package com.cs446.foodiehub.model;

/**
 * Created by Alex on 15-07-13.
 */
public class FoodSection extends FoodItem {

    private boolean isSection = false;

    private String restaurantTitle;

    private String table;

    private String date;

    public static FoodSection generateSectionMenu(String restaurantTitle, String date, String table){
        FoodSection foodSection = new FoodSection();
        foodSection.isSection = true;
        foodSection.restaurantTitle = restaurantTitle;
        foodSection.date = date;
        foodSection.table = table;
        return foodSection;
    }

    public static FoodSection generateSectionMenu(String restaurantTitle, String date){
        return generateSectionMenu(restaurantTitle, date, null);
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
}
