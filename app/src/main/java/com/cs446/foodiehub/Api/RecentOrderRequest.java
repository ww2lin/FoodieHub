package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-07-12.
 */
public class RecentOrderRequest extends HttpClient {
    private static String endpoint = "api/restaurants/get-recent-orders";

    public static void getRecentOrder(String resturantId, ServerResponse serverResponse) {
        RequestParams data = new RequestParams();
        data.put("restaurantid", resturantId);
        ExecuteGetRequest(endpoint, data, serverResponse);
    }
}