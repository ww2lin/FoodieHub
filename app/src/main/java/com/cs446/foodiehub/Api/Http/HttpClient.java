package com.cs446.foodiehub.Api.Http;

import com.cs446.foodiehub.Interface.SeverResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by Alex on 15-06-12.
 */
public class HttpClient {
    private static final String hostUrl = "http://104.236.250.210:8000/api/";

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

    private HttpClient() {
    }

    public static void ExecuteGetRequest(String url,final SeverResponse severResponse) {
        HttpClient.ExecuteGetRequest(hostUrl+url, severResponse);
    }

    public static void ExecuteGetRequest(String url, RequestParams params,final SeverResponse severResponse) {
        client.get(hostUrl+url, params, executeRequest(severResponse));
    }

    public static void ExecutePostRequest(String url, RequestParams params, final SeverResponse severResponse) {
        client.post(hostUrl+url, params, executeRequest(severResponse));
    }

    private static TextHttpResponseHandler executeRequest(final SeverResponse severResponse) {
        return (new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                severResponse.onFailure(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                severResponse.onSuccess(statusCode, responseString);
            }
        });
    }

}
