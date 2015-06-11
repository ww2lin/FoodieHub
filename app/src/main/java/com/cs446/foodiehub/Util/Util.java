package com.cs446.foodiehub.Util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by Alex on 15-06-11.
 */
public class Util {
    private static int DEVICE_API_VERSION = Build.VERSION.SDK_INT;

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
}
