package com.cs446.foodiehub.model.server;

import android.os.Parcel;
import android.os.Parcelable;

import com.cs446.foodiehub.model.FoodItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-06-10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuItem extends FoodItem implements Parcelable {
    private String mId;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("picture")
    private String mImage;
    @JsonProperty("price")
    private String mPrice;
    @JsonProperty("category")
    private String mCategory;
    @JsonProperty("_id")
    private String mServerId;

    @JsonIgnore
    private boolean checked;

    public MenuItem() {
    }

    public MenuItem(String image, String price){
        mImage = image;
        mPrice = price;
    }

    public MenuItem(String mId, String mName, String mDescription, String mImage, String mPrice, String mCategory, String mServerId, boolean checked) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.mServerId = mServerId;
        this.checked = checked;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getCategory() {
        return mCategory;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getServerId() {
        return mServerId;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Parcelable interface
     */
    @Override
    public int describeContents() {
        return 0;
    }

    protected MenuItem(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mImage);
        dest.writeString(mPrice);
        dest.writeString(mCategory);
        dest.writeString(mServerId);
    }

    public void readFromParcel(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
        mPrice = in.readString();
        mCategory = in.readString();
        mServerId = in.readString();
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        public MenuItem createFromParcel(Parcel source) {
            return new MenuItem(source);
        }

        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };


}
