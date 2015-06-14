package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.SeverResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-06-12.
 */
public class RestaurantRequest {
    private static String mRestaruantUrl = "restaurants";
    private double mLatitude;
    private double mLongitude;

    public static void getRestaurants(SeverResponse severResponse){
        RequestParams data = new RequestParams();
        data.put("latitude", "43.47210");
        data.put("longitude", "-80.54395");
        HttpClient.ExecuteGetRequest(mRestaruantUrl, data, severResponse);
    }

}
