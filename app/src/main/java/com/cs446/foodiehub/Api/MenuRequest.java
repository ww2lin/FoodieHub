package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-06-14.
 */
public class MenuRequest extends HttpClient{

    private static final String mMenuUrl = "api/menu";
    private static final String KEY_RESTAURANT_ID="restaurantid";
    private double mLatitude;
    private double mLongitude;

    public static void getMenu(String restaurantId, ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put(KEY_RESTAURANT_ID, restaurantId);
        ExecuteGetRequest(mMenuUrl, data, serverResponse);
    }
}
