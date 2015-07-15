package com.cs446.foodiehub.Thread;

import android.app.Activity;
import android.widget.Toast;

import com.cs446.foodiehub.Api.FoodOrderRequest;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.server.FoodStatus;

import org.json.JSONObject;

/**
 * Created by Alex on 15-07-14.
 */

// Use to check a food status on the server.
public class FoodStatusThread extends Thread{
    private final String orderId;

    private FoodStatus foodStatus = FoodStatus.PROGRESS;

    private Activity activity;

    private boolean isKilled = false;

    public FoodStatusThread(Activity activity, String orderId, String status){
        this.orderId = orderId;
        foodStatus = FoodStatus.valueOf(status.toUpperCase());
        this.activity = activity;
    }

    @Override
    public void run() {
        if (orderId != null) {
            while (foodStatus == FoodStatus.PROGRESS && !isKilled) {
                FoodOrderRequest.getFoodStatus(orderId, serverResponse);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private ServerResponse serverResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                if (jsonObject.has("status")){
                    foodStatus = FoodStatus.valueOf(jsonObject.get("status").toString().toUpperCase());
                }

                switch (foodStatus){
                    case COMPLETED:
                        print(R.string.order_ready);
                        break;
                    case FAILED:
                        print(R.string.order_canceled);
                        break;
                    case PROGRESS:
                    default:
                        print(R.string.order_progress);
                        // contintue to check
                }

            } catch (Exception e){

            }

        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            // ignore
        }
    };

    private void print (final int stringid){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, Util.getStringById(activity, stringid), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void killThread(){
        isKilled = true;
    }
}
