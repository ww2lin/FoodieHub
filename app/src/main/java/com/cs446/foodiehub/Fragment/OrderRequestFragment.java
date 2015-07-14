package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foodiehub.Adapter.FoodRequestAdapter;
import com.cs446.foodiehub.Api.FoodOrderRequest;
import com.cs446.foodiehub.Api.LoginRequest;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.FoodOrder;
import com.cs446.foodiehub.model.FoodOrderWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-13.
 */
public class OrderRequestFragment extends FoodieHubFragment {

    private ListView mListView;
    private FoodRequestAdapter orderHistoryAdapter;

    public OrderRequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_order_history, container, false);

        // Fetch the list of restaurants
        FoodOrderRequest.getRestaurantOrders(LoginRequest.getResturant(),mServerResponse);

        mListView = (ListView) rootView.findViewById(R.id.lv_order_history);

        return rootView;
    }

    private ServerResponse mServerResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                ArrayList<FoodOrderWrapper> foodOrderWrappers = Util.getMapper().readValue(responseString, new TypeReference<ArrayList<FoodOrderWrapper>>() {
                });

                orderHistoryAdapter = new FoodRequestAdapter(getActivity());
                for (FoodOrderWrapper foodOrderWrapper : foodOrderWrappers){
                    orderHistoryAdapter.addSectionHeaderItem(foodOrderWrapper);
                    for (FoodOrder foodOrder : foodOrderWrapper.getFoodOrders()) {
                        orderHistoryAdapter.addItem(foodOrder, foodOrderWrapper.getOrderId());
                    }
                }

                mListView.setAdapter(orderHistoryAdapter);
            } catch (Exception e){
                Logger.e("json error", e);
            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            Toast.makeText(getActivity(), "fail: "+responseString, Toast.LENGTH_SHORT).show();
        }
    };
}

