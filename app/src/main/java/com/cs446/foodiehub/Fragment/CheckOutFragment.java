package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.FoodOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 15-06-09.
 */
public class CheckoutFragment extends FoodieHubFragment {

    private ArrayList<FoodOrder> mFoodOrders;
    private ArrayAdapter<String> mOrders;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        mFoodOrders = MenuGalleryFragment.getFoodOrder(getArguments());

        mListView = (ListView) rootView.findViewById(R.id.lv_orders);


        List<String> temp = new ArrayList<>();
        for (FoodOrder foodOrder : mFoodOrders){
            temp.add(foodOrder.getUrl());
        }

        mOrders = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, temp);

        mListView.setAdapter(mOrders);

        return rootView;
    }

}
