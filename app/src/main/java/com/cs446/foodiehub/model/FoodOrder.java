package com.cs446.foodiehub.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 15-06-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodOrder extends FoodItem implements Parcelable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("_id")
    private String mServerId;
    @JsonProperty("picture")
    private String mImage;
    @JsonProperty("note")
    private String mNote;
    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("price")
    private String mPrice;

    public FoodOrder(){}

    public FoodOrder(String name, String serverId, String image, String price, String description) {
        this.name = name;
        this.mServerId = serverId;
        this.mImage = image;
        this.mPrice = price;
        this.mDescription = description;
    }

    public String getServerId() {
        return mServerId;
    }

    public String getImage() {
        return mImage;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String mNote) {
        this.mNote = mNote;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return name;
    }

    /**
     * Parcelable interface
     */
    @Override
    public int describeContents() {
        return 0;
    }

    protected FoodOrder(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(mServerId);
        dest.writeString(mImage);
        dest.writeString(mNote);
        dest.writeString(mPrice);
        dest.writeString(mDescription);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        mServerId = in.readString();
        mImage = in.readString();
        mNote = in.readString();
        mPrice = in.readString();
        mDescription = in.readString();
    }

    public static final Parcelable.Creator<FoodOrder> CREATOR = new Parcelable.Creator<FoodOrder>() {
        public FoodOrder createFromParcel(Parcel source) {
            return new FoodOrder(source);
        }

        public FoodOrder[] newArray(int size) {
            return new FoodOrder[size];
        }
    };
}
