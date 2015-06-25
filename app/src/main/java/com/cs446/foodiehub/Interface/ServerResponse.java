package com.cs446.foodiehub.Interface;

/**
 * Created by Alex on 15-06-12.
 */
public interface ServerResponse {
    void onSuccess(int statusCode, String responseString);

    void onFailure(int statusCode, String responseString);
}
