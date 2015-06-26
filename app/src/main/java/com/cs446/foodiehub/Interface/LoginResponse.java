package com.cs446.foodiehub.Interface;

/**
 * Created by Alex on 15-06-25.
 */
public interface LoginResponse {

    void success();
    void loginFail(int statusCode, String responseString);
}
