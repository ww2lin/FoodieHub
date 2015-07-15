package com.cs446.foodiehub.Util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Alex on 15-06-11.
 */
public class Util<T> {
    private static final int DEVICE_API_VERSION = Build.VERSION.SDK_INT;
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * Check if the api is supported
     * @param apiVersion
     * @return true if the api is supported
     */
    public static boolean isSupported (int apiVersion){
        return DEVICE_API_VERSION > apiVersion;
    }

    public static int getAPIVersion(){
        return DEVICE_API_VERSION;
    }

    @TargetApi(21)
    public static Drawable getDrawable(Context context, int id) {
        if (Util.getAPIVersion() >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else return context.getResources().getDrawable(id);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static String getStringById(Context context, int resourceId){
        return context.getResources().getString(resourceId);
    }
}
