package com.cs446.foodiehub.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 15-06-11.
 */
public class FoodOrder implements Parcelable {
    private String name;
    private String mServerId;
    private String mUrl;
    private String mNote;
    private String mDescription;
    private String mPrice;
    private Long mQuantity = 1L;

    public FoodOrder(String name, String serverId, String url, String price, String description) {
        this.name = name;
        this.mServerId = serverId;
        this.mUrl = url;
        this.mPrice = price;
        this.mDescription = description;
    }

    public String getServerId() {
        return mServerId;
    }

    public String getUrl() {
        return mUrl;
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

    public Long getQuantity() {
        return mQuantity;
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
        dest.writeString(mUrl);
        dest.writeString(mNote);
        dest.writeString(mPrice);
        dest.writeLong(mQuantity);
        dest.writeString(mDescription);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        mServerId = in.readString();
        mUrl = in.readString();
        mNote = in.readString();
        mPrice = in.readString();
        mQuantity = in.readLong();
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
