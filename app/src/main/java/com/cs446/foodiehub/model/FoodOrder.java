package com.cs446.foodiehub.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 15-06-11.
 */
public class FoodOrder implements Parcelable {
    private String mServerId;
    private String mUrl;
    private String mNote;

    public FoodOrder(String mServerId, String mUrl) {
        this.mServerId = mServerId;
        this.mUrl = mUrl;
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
        dest.writeString(mServerId);
        dest.writeString(mUrl);
        dest.writeString(mNote);
    }

    public void readFromParcel(Parcel in) {
        mServerId = in.readString();
        mUrl = in.readString();
        mNote = in.readString();
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
