package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.NavigationItem;

/**
 * Created by Alex on 15-06-09.
 */
public class NavigationAdapter extends BaseAdapter {

    NavigationItem[] mFragmentTypes;
    Context context;
    // View lookup cache
    private static class ViewHolder {
        TextView name;
        ImageView icon;
    }

    public NavigationAdapter(Context context, NavigationItem[] mFragmentTypes) {
        this.mFragmentTypes = mFragmentTypes;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NavigationItem navigationItem = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.panel_navigation, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.navigation_item);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.navigation_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(navigationItem.getFragmentType().getName());
        viewHolder.icon.setImageDrawable(Util.getDrawable(context,navigationItem.getDrawableId()));
        return convertView;
    }

    @Override
    public int getCount() {
        return mFragmentTypes.length;
    }

    @Override
    public NavigationItem getItem(int position) {
        return mFragmentTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
