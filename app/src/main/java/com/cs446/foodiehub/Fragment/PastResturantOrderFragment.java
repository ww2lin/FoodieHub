package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cs446.foodiehub.Adapter.PastOrderAdapter;
import com.cs446.foodiehub.Api.RecentOrderRequest;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.MenuItem;
import com.cs446.foodiehub.model.RecentOrder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-12.
 */
public class PastResturantOrderFragment extends FoodieHubFragment {

    private ListView mListView;
    private PastOrderAdapter mRestaurants;
    private String mSelectedRestaurantId;

    private static final String EXTRA_SELECTED_RESTAURANT = "extra_selected_restaurant";

    public PastResturantOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_past_order, container, false);

        String resturantId = MenuGalleryFragment.getRestrauntId(getArguments());

        // Fetch the list of restaurants
        RecentOrderRequest.getRecentOrder(resturantId, mServerResponse);

        mListView = (ListView) rootView.findViewById(R.id.lv_past_order);

        return rootView;
    }

    public static String getMenu(Bundle bundle) {
        return bundle.getString(EXTRA_SELECTED_RESTAURANT);
    }

    private ServerResponse mServerResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                ArrayList<RecentOrder> recentOrders = Util.getMapper().readValue(responseString, new TypeReference<ArrayList<RecentOrder>>() {
                });

                ArrayList<MenuItem> menuItems = new ArrayList<>(recentOrders.size());
                for (RecentOrder recentOrder : recentOrders){
                    menuItems.addAll(recentOrder.getMenuItems());
                }
                mRestaurants = new PastOrderAdapter(getActivity(), menuItems);
                mListView.setAdapter(mRestaurants);
            } catch (Exception e){

            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
        }
    };
}
