package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs446.foodiehub.Adapter.FoodOrderAdapter;
import com.cs446.foodiehub.Api.SubmitFoodOrder;
import com.cs446.foodiehub.Fragment.base.MenuFoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.FoodOrder;
import com.cs446.foodiehub.model.server.OrderItem;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-09.
 */
public class CheckoutFragment extends MenuFoodieHubFragment {

    private ArrayList<FoodOrder> mFoodOrders;
    private FoodOrderAdapter mFoodOrderAdapter;
    private String mTableId;
    private String mResturantid;
    private ListView mListView;
    private TextView mTotalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        mFoodOrders = MenuGalleryFragment.getFoodOrder(getArguments());

        mListView = (ListView) rootView.findViewById(R.id.lv_orders);

        mFoodOrderAdapter = new FoodOrderAdapter(getActivity(), mFoodOrders);

        mListView.setAdapter(mFoodOrderAdapter);

        //getActivity().getMenuInflater().inflate(R.menu.fragment_checkout,);

        mTotalPrice = (TextView) rootView.findViewById(R.id.tv_total_price);
        mTotalPrice.setText(MenuGalleryFragment.getTotalPrice(getArguments()).toString());

        mTableId = MenuGalleryFragment.getTableId(getArguments());
        mResturantid = MenuGalleryFragment.getRestrauntId(getArguments());

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_checkout, menu);
    }

    @Override
    public boolean populateCustomActionBar(){
        return true;
    }

    @Override
    public boolean onMenuSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_food_checkout) {
            JSONArray orderItems = new JSONArray();
            for (FoodOrder foodOrder : mFoodOrders){
                orderItems.put(OrderItem.toJSON(foodOrder.getServerId(), foodOrder.getNote()));
            }
            SubmitFoodOrder.submitOrder(mResturantid, mTableId, orderItems, mResponse);
            return true;
        }
        return false;
    }

    ServerResponse mResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            // redirect to home page.
            Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
        }
    };
}
