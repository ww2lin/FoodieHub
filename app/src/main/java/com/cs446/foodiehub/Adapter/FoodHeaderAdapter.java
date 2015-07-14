package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.model.FoodItem;
import com.cs446.foodiehub.model.FoodSection;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-13.
 */
public abstract class FoodHeaderAdapter extends BaseAdapter {

    protected static final int TYPE_ITEM = 0;
    protected static final int TYPE_SEPARATOR = 1;

    protected ArrayList<FoodItem> mPastOrders = new ArrayList<>();
    protected Context context;
    protected RequestManager mGlide;

    protected LayoutInflater mInflater;



    public FoodHeaderAdapter(Context context) {
        this.context = context;
        this.mPastOrders = new ArrayList<>();
        mGlide = Glide.with(context);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        return mPastOrders.get(position) instanceof FoodSection ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mPastOrders.size();
    }

    @Override
    public FoodItem getItem(int position) {
        return mPastOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
