package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cs446.foodiehub.model.MenuItem;
import com.cs446.foodiehub.model.Restaurant;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-14.
 */
public class RestaurantAdapter extends BaseAdapter{
    private Context context;
    private final ArrayList<Restaurant> mRestaurants;

    private static class ViewHolder {
        TextView textView;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> foodOrders) {
        this.context = context;
        this.mRestaurants = foodOrders;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);

            viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mRestaurants.get(position).getName());
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
