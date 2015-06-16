package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cs446.foodiehub.Fragment.FragmentType;
import com.cs446.foodiehub.R;

/**
 * Created by Alex on 15-06-09.
 */
public class FragmentTypeAdapter extends BaseAdapter {

    FragmentType[] mFragmentTypes;
    Context context;
    // View lookup cache
    private static class ViewHolder {
        TextView name;
    }

    public FragmentTypeAdapter(Context context, FragmentType[] mFragmentTypes) {
        this.mFragmentTypes = mFragmentTypes;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FragmentType fragmentType = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.panel_navigation, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.navigation_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(fragmentType.getName());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return mFragmentTypes.length;
    }

    @Override
    public FragmentType getItem(int position) {
        return mFragmentTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
