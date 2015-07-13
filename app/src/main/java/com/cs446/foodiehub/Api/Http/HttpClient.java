package com.cs446.foodiehub.Api.Http;

import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

/**
 * Created by Alex on 15-06-12.
 */
public class HttpClient {
    private static final String hostUrl = "http://104.236.250.210:8000/";

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
    private static String KEY_BEARER = "Bearer";
    private static String  KEY_AUTHORIZATION = "Authorization";

    protected HttpClient() {
    }

    protected static void ExecuteGetRequest(String url,final ServerResponse serverResponse) {
        client.get(hostUrl + url, executeRequest(serverResponse));
    }

    protected static void ExecuteGetRequest(String url, RequestParams params,final ServerResponse serverResponse) {
        client.get(hostUrl + url, params, executeRequest(serverResponse));
    }

    protected static void ExecutePostRequest(String url, RequestParams params, final ServerResponse serverResponse) {
        client.post(hostUrl + url, params, executeRequest(serverResponse));
    }

    protected static void ExecuteJsonPostRequest(String url, JSONObject jsonObject, final ServerResponse serverResponse) throws Exception {
        client.post(null, hostUrl + url, new StringEntity(jsonObject.toString()), "application/json", executeRequest(serverResponse));
    }

    private static TextHttpResponseHandler executeRequest(final ServerResponse serverResponse) {
        return (new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                serverResponse.onFailure(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                serverResponse.onSuccess(statusCode, responseString);
            }
        });
    }

    protected static void setToken(String token){
        client.addHeader(KEY_AUTHORIZATION, KEY_BEARER+" "+token);
    }

}
