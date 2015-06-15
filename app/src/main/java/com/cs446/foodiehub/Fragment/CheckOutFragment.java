package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.cs446.foodiehub.Adapter.FoodOrderAdapter;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.FoodOrder;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-09.
 */
public class CheckoutFragment extends FoodieHubFragment {

    private ArrayList<FoodOrder> mFoodOrders;
    private FoodOrderAdapter mFoodOrderAdapter;
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
}
