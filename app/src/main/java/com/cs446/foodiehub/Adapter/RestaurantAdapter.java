package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.server.Restaurant;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-14.
 */
public class RestaurantAdapter extends BaseAdapter{
    private Context context;
    private final ArrayList<Restaurant> mRestaurants;
    private RequestManager mGlide;

    private static class ViewHolder {
        TextView restaurantName;
        ImageView restaurantImage;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> foodOrders) {
        this.context = context;
        this.mRestaurants = foodOrders;
        mGlide = Glide.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_restaurants, null);

            viewHolder.restaurantName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.restaurantImage = (ImageView) convertView.findViewById(R.id.iv_restaurant);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Restaurant restaurant = mRestaurants.get(position);
        viewHolder.restaurantName.setText(restaurant.getName());
        mGlide.load(restaurant.getImg()).centerCrop().into(viewHolder.restaurantImage);
        return convertView;
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return mRestaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
