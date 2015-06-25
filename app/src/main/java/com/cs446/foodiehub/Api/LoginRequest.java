package com.cs446.foodiehub.Api;

import android.util.Log;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.LoginResponse;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * Created by Alex on 15-06-12.
 */
public class LoginRequest extends HttpClient{

    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LOGIN = "auth/local";
    private static LoginResponse callback;

    public static void login(String username, String password, LoginResponse loginResponse){
        RequestParams data = new RequestParams();
        data.put(KEY_EMAIL, username);
        data.put(KEY_PASSWORD, password);
        callback = loginResponse;
        HttpClient.ExecutePostRequest(KEY_LOGIN, data, response);
    }

    private static ServerResponse response = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                JSONObject jsonObj = new JSONObject(responseString);
                String token = jsonObj.getString("token");

                if (token != null) {
                    setToken(token);
                    callback.success(token);
                }
            } catch (Exception e){
            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            try {
                JSONObject jsonObj = new JSONObject(responseString);
                String msg = jsonObj.getString("message");
                callback.loginFail(statusCode, msg);
            } catch (Exception e){
                callback.loginFail(statusCode, responseString);
            }
        }
    };


}
