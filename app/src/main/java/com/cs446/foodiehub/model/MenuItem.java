package com.cs446.foodiehub.model;

/**
 * Created by Alex on 15-06-10.
 */
public class MenuItem {
    private String mId;
    private String mName;
    private String mDescription;
    private String mImage;
    private String mPrice;
    private String mCategory;
    private String mServerId;

    private boolean checked;

    public MenuItem(String image, String price){
        mImage = image;
        mPrice = price;
    }

    public MenuItem(String mName, String mDescription, String mImage, String mPrice, String mCategory, boolean checked) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.checked = checked;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmCategory() {
        return mCategory;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
