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

import com.cs446.foodiehub.Adapter.CheckoutFoodOrderAdapter;
import com.cs446.foodiehub.Api.SubmitFoodOrder;
import com.cs446.foodiehub.Fragment.base.MenuFoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Thread.FoodStatusThread;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.server.FoodOrder;
import com.cs446.foodiehub.model.server.OrderItem;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-09.
 */
public class CheckoutFragment extends MenuFoodieHubFragment {

    private ArrayList<FoodOrder> mFoodOrders;
    private CheckoutFoodOrderAdapter mCheckoutFoodOrderAdapter;
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

        mCheckoutFoodOrderAdapter = new CheckoutFoodOrderAdapter(getActivity(), mFoodOrders);

        mListView.setAdapter(mCheckoutFoodOrderAdapter);

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
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                String message = null;
                String total = null;
                String foodStatus = null;
                String orderId = null;

                if (jsonObject.has("message")) message = jsonObject.get("message").toString();
                if (jsonObject.has("total")) total = jsonObject.get("total").toString();
                if (jsonObject.has("status")) foodStatus = jsonObject.get("status").toString();
                if (jsonObject.has("orderid")) orderId = jsonObject.get("orderid").toString();

                Toast.makeText(getActivity(), message+" "+ Util.getStringById(getActivity(), R.string.total_cost_with_tax)+" "+total, Toast.LENGTH_SHORT).show();

                new FoodStatusThread(getActivity(), orderId, foodStatus).start();

            } catch (Exception e) {
                Logger.e("error", e);
            }
        }
        @Override
        public void onFailure(int statusCode, String responseString) {
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                if (jsonObject.has("message"))
                    Toast.makeText(getActivity(), jsonObject.get("message").toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e){

            }
        }
    };

    @Override
    protected String getTitle() {
        return FragmentType.RESTAURANT.getName();
    }
}
