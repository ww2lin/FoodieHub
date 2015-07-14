package com.cs446.foodiehub.Api;

import com.cs446.foodiehub.Api.Http.HttpClient;
import com.cs446.foodiehub.Interface.ServerResponse;

/**
 * Created by Alex on 15-06-25.
 */
public class UserRequest extends HttpClient{

    private static final String mRestaruantUrl = "api/users/me";

    public static void getUser(ServerResponse serverResponse){
        ExecuteGetRequest(mRestaruantUrl, serverResponse);
    }
}
