package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.SeverResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-06-14.
 */
public class MenuRequest {

    private static String mMenuUrl = "menu";
    private static final String KEY_RESTAURANT_ID="restaurantid";
    private double mLatitude;
    private double mLongitude;

    public static void getMenu(String restaurantId, SeverResponse severResponse){
        RequestParams data = new RequestParams();
        data.put(KEY_RESTAURANT_ID, restaurantId);
        HttpClient.ExecuteGetRequest(mMenuUrl, data, severResponse);
    }
}
