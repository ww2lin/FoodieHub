package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.model.server.FoodStatus;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 15-07-12.
 */
public class FoodOrderRequest extends HttpClient {

    private static final String recentOrderEndpoint = "api/restaurants/get-restaurant-orders";

    private static final String completeOrder = "api/order/sent-order";

    private static final String cancelOrder = "api/order/un-send-order";

    private static final String foodStatus = "api/order/status";

    public static void getRestaurantOrders(String resturantId, ServerResponse serverResponse) {
        RequestParams data = new RequestParams();
        data.put("restaurantid", resturantId);
        data.put("status-filter", FoodStatus.PROGRESS.getName());
        ExecuteGetRequest(recentOrderEndpoint, data, serverResponse);
    }

    public static void orderCompleted(String orderId, ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put("orderid", orderId);
        ExecutePostRequest(completeOrder, data, serverResponse);
    }

    public static void orderCancel(String orderId, ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put("orderid", orderId);
        ExecutePostRequest(cancelOrder, data, serverResponse);
    }

    public static void getFoodStatus(String orderId, ServerResponse serverResponse){
        RequestParams data = new RequestParams();
        data.put("orderid", orderId);
        ExecuteThreadSafePostRequest(foodStatus, data, serverResponse);
    }
}