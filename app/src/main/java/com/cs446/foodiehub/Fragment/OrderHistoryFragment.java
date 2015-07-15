package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foodiehub.Adapter.OrderHistoryAdapter;
import com.cs446.foodiehub.Api.SubmitFoodOrder;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.server.MenuItem;
import com.cs446.foodiehub.model.server.RecentOrder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-09.
 */
public class OrderHistoryFragment extends FoodieHubFragment {

    private ListView mListView;
    private OrderHistoryAdapter orderHistoryAdapter;

    public OrderHistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_order_history, container, false);

        // Fetch the list of restaurants
        SubmitFoodOrder.getPastOrders(mServerResponse);

        mListView = (ListView) rootView.findViewById(R.id.lv_order_history);

        return rootView;
    }

    private ServerResponse mServerResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                ArrayList<RecentOrder> recentOrders = Util.getMapper().readValue(responseString, new TypeReference<ArrayList<RecentOrder>>() {
                });

                orderHistoryAdapter = new OrderHistoryAdapter(getActivity());
                for (RecentOrder recentOrder : recentOrders){
                    orderHistoryAdapter.addSectionHeaderItem(recentOrder);
                    for (MenuItem menuItem : recentOrder.getMenuItems()) {
                        orderHistoryAdapter.addItem(menuItem);
                    }
                }

                mListView.setAdapter(orderHistoryAdapter);
            } catch (Exception e){

            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            Toast.makeText(getActivity(), "fail: "+responseString, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected String getTitle() {
        return FragmentType.ORDER_HISTORY.getName();
    }
}
