package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Alex on 15-07-12.
 */
public class SubmitFoodOrder extends HttpClient {
    private static final String endpoint = "api/order";

    public static void submitOrder(String resturantId, String tableId, JSONArray order, ServerResponse serverResponse){
        try {
            JSONObject data = new JSONObject();
            data.put("restaurantid", resturantId);
            data.put("tablenumber", tableId);
            data.put("items", order);
            ExecuteJsonPostRequest(endpoint, data, serverResponse);
        } catch (Exception e){

        }
    }

    /**
     * Get an list of past order by the current user.
     * @param serverResponse
     */
    public static void getPastOrders(ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        ExecuteGetRequest(endpoint, data, serverResponse);
    }


}
