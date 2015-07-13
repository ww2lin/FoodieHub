package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

/**
 * Created by Alex on 15-07-12.
 */
public class SubmitFoodOrder extends HttpClient {
    private static String endpoint = "api/order";

    public static void submitOrder(String resturantId, String tableId, JSONArray order, ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put("restaurantid", resturantId);
        data.put("tablenumber", tableId);
        data.put("items", order);
        ExecuteGetRequest(endpoint, data, serverResponse);
    }
}
