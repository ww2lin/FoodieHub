package com.cs446.foodiehub.model.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 15-06-26.
 */

// model to use to sent the information to the server
//{order_items:[{id, quantity, custom_note}]}
public class OrderItem {

    public static JSONObject toJSON(String id, String note){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("note", note);
            return jsonObject;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
