package com.cs446.foodiehub.Api.Http;

import com.cs446.foodiehub.Interface.ServerResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by Alex on 15-06-12.
 */
public class HttpClient {
    private static final String hostUrl = "http://104.236.250.210:8000/";

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

    // The token is shared across all Request
    private static String token;

    protected HttpClient() {
    }

    protected static void ExecuteGetRequest(String url,final ServerResponse serverResponse) {
        HttpClient.ExecuteGetRequest(hostUrl+url, serverResponse);
    }

    protected static void ExecuteGetRequest(String url, RequestParams params,final ServerResponse serverResponse) {
        client.get(hostUrl + url, params, executeRequest(serverResponse));
    }

    protected static void ExecutePostRequest(String url, RequestParams params, final ServerResponse serverResponse) {
        client.post(hostUrl + url, params, executeRequest(serverResponse));
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
        HttpClient.token = token;
    }

}
