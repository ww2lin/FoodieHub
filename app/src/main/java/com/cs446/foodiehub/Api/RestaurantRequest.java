package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-06-12.
 */
public class RestaurantRequest extends HttpClient{
    private static final String mRestaruantUrl = "api/restaurants";

    public static void getRestaurants(ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put("latitude", "43.47210");
        data.put("longitude", "-80.54395");
        ExecuteGetRequest(mRestaruantUrl, data, serverResponse);
    }

}
